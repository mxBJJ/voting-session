package com.maxmendes.votacaoapi.contract.facade;

import com.maxmendes.votacaoapi.contract.mapper.VoteMapper;
import com.maxmendes.votacaoapi.contract.model.request.VoteRequest;
import com.maxmendes.votacaoapi.impl.facade.VoteFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class VoteContractFacade {

    private final VoteFacade voteFacade;

    public Mono<Void> vote(VoteRequest voteRequest, String sessionId) {
        return voteFacade.vote(VoteMapper.mapToModel(voteRequest), sessionId)
                .then(Mono.empty());
    }
}
