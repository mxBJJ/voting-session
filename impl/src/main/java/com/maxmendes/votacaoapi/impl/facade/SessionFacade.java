package com.maxmendes.votacaoapi.impl.facade;

import com.maxmendes.votacaoapi.impl.model.SessionModel;
import com.maxmendes.votacaoapi.impl.model.VoteModel;
import com.maxmendes.votacaoapi.impl.service.SessionService;
import com.maxmendes.votacaoapi.impl.service.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class SessionFacade {

    private final SessionService sessionService;

    public Mono<SessionModel> openSession(SessionModel sessionModel) {
        return sessionService.openSession(sessionModel);
    }
}
