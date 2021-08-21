package com.maxmendes.votacaoapi.contract.mapper;

import com.maxmendes.votacaoapi.contract.model.request.SessionRequest;
import com.maxmendes.votacaoapi.contract.model.response.SessionResponse;
import com.maxmendes.votacaoapi.impl.model.SessionModel;

import java.util.Optional;

public class SessionMapper {

    public static SessionModel mapToModel(SessionRequest sessionRequest) {
        return Optional.ofNullable(sessionRequest)
                .map(request -> SessionModel.builder()
                        .duration(request.getDuration())
                        .build())
                .orElse(null);
    }

    public static SessionResponse mapToResponse(SessionModel sessionModel) {
        return Optional.ofNullable(sessionModel)
                .map(model -> SessionResponse.builder()
                        .id(model.getId())
                        .duration(model.getDuration())
                        .createdAt(model.getCreatedAt())
                        .cpfList(model.getCpfList())
                        .build())
                .orElse(null);
    }
}
