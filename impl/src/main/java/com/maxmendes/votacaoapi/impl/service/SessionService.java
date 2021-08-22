package com.maxmendes.votacaoapi.impl.service;

import com.maxmendes.votacaoapi.impl.enums.VoteOptions;
import com.maxmendes.votacaoapi.impl.error.NotFoundException;
import com.maxmendes.votacaoapi.impl.exception.ExceptionMessageBuilder;
import com.maxmendes.votacaoapi.impl.kafka.producer.SessionKafkaProducer;
import com.maxmendes.votacaoapi.impl.mapper.SessionMapper;
import com.maxmendes.votacaoapi.impl.model.SessionModel;
import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;
import com.maxmendes.votacaoapi.impl.model.entity.VoteEntity;
import com.maxmendes.votacaoapi.impl.repository.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ExceptionMessageBuilder messageBuilder;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private final SessionKafkaProducer sessionKafkaProducer;

    public Mono<SessionModel> openSession(SessionModel sessionModel) {
        return sessionRepository.save(SessionMapper.mapToEntity(sessionModel))
                .flatMap(sessionEntity -> {
                    schedule(sessionEntity);
                    return Mono.just(sessionEntity);
                })
                .map(SessionMapper::mapToModel);
    }

    public Mono<SessionEntity> findSessionById(String id) {
        return sessionRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(messageBuilder.getNotFound())));
    }

    public Mono<SessionEntity> save(SessionEntity sessionEntity) {
        return sessionRepository.save(sessionEntity);
    }

    private void schedule(SessionEntity sessionEntity) {
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                sessionRepository.findById(sessionEntity.getId())
                        .flatMap(entity -> sessionResult(entity)).block();
            }
        }, sessionEntity.getDuration(), TimeUnit.MINUTES);
    }

    private Mono<SessionEntity> sessionResult(SessionEntity entity) {
        var totalYes = 0;
        var totalNo = 0;
        List<VoteEntity> votes = new ArrayList<>();
        if (!ObjectUtils.isEmpty(entity.getVotes())) {
            votes = entity.getVotes();
            totalYes = (int) votes.stream()
                    .filter(voteEntity -> voteEntity.getVote().equals(VoteOptions.SIM)).count();
            totalNo = (int) votes.stream()
                    .filter(voteEntity -> voteEntity.getVote().equals(VoteOptions.NAO)).count();
        }

        publishKafka(entity, totalYes, totalNo);
        return Mono.empty();
    }

    private void publishKafka(SessionEntity sessionEntity, Integer totalYes, Integer totalNo) {
        sessionKafkaProducer.publish(SessionMapper.mapToKafkaEntity(sessionEntity, totalYes, totalNo));
    }

}
