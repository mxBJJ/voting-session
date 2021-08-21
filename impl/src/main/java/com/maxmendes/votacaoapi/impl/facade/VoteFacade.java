package com.maxmendes.votacaoapi.impl.facade;

import com.maxmendes.votacaoapi.impl.model.VoteModel;
import com.maxmendes.votacaoapi.impl.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class VoteFacade {

    private final VoteService voteService;

    public Mono<VoteModel> vote(VoteModel voteModel, String sessionId) {
        return voteService.vote(voteModel, sessionId);
    }
}
