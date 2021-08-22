package com.maxmendes.votacaoapi.impl.service;

import com.maxmendes.votacaoapi.impl.error.AlreadyVoteException;
import com.maxmendes.votacaoapi.impl.exception.ExceptionMessageBuilder;
import com.maxmendes.votacaoapi.impl.mapper.SessionMapper;
import com.maxmendes.votacaoapi.impl.mapper.VoteMapper;
import com.maxmendes.votacaoapi.impl.model.VoteModel;
import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;
import com.maxmendes.votacaoapi.impl.model.entity.VoteEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class VoteService {

    private final SessionService sessionService;
    private final ExceptionMessageBuilder messageBuilder;

    public Mono<VoteModel> vote(VoteModel voteModel, String sessionId) {
        return sessionService.findSessionById(sessionId)
                .filter(sessionEntity -> cpfAlreadyVote(sessionEntity, voteModel))
                .flatMap(sessionEntity -> voteToCpf(voteModel.getCpf(), sessionEntity))
                .flatMap(sessionEntity -> sessionEntityFactory(sessionEntity, VoteMapper.mapToEntity(voteModel)))
                .map(SessionMapper::mapToModel)
                .switchIfEmpty(Mono.error(new AlreadyVoteException(messageBuilder.getAlreadyVote())))
                .then(Mono.just(voteModel));
    }

    private Mono<SessionEntity> sessionEntityFactory(SessionEntity sessionEntity, VoteEntity voteEntity) {
        List<VoteEntity> votesList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(sessionEntity.getVotes())) votesList = sessionEntity.getVotes();
        votesList.add(voteEntity);
        sessionEntity.setVotes(votesList);
        return sessionService.save(sessionEntity);
    }

    private Boolean cpfAlreadyVote(SessionEntity sessionEntity, VoteModel voteModel) {
        if(ObjectUtils.isEmpty(sessionEntity.getCpfList())) return true;
        return !sessionEntity.getCpfList().contains(voteModel.getCpf());
    }

    private Mono<SessionEntity> voteToCpf(String cpf, SessionEntity sessionEntity) {
        List<String> cpfList = new ArrayList<>();
        if (!ObjectUtils.isEmpty(cpfList)) cpfList = sessionEntity.getCpfList();
        cpfList.add(cpf);
        sessionEntity.setCpfList(cpfList);
        return Mono.just(sessionEntity);
    }

}
