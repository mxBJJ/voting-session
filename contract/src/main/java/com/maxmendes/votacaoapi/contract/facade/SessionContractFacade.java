package com.maxmendes.votacaoapi.contract.facade;

import com.maxmendes.votacaoapi.contract.mapper.SessionMapper;
import com.maxmendes.votacaoapi.contract.model.request.SessionRequest;
import com.maxmendes.votacaoapi.contract.model.response.SessionResponse;
import com.maxmendes.votacaoapi.impl.facade.SessionFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SessionContractFacade {

    private final SessionFacade sessionFacade;

    public Mono<SessionResponse> openSession(SessionRequest sessionRequest) {
        return sessionFacade.openSession(SessionMapper.mapToModel(sessionRequest))
                .map(SessionMapper::mapToResponse);
    }
}
