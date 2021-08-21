package com.maxmendes.votacaoapi.impl.mapper;

import com.maxmendes.votacaoapi.impl.model.SessionModel;
import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class SessionMapper {

    public static SessionEntity mapToEntity(SessionModel sessionModel) {
        return Optional.ofNullable(sessionModel)
                .map(model -> SessionEntity.builder()
                        .duration(model.getDuration())
                        .createdAt(LocalDateTime.now())
                        .cpfList(model.getCpfList())
                        .topic(Optional.ofNullable(model.getTopicModel())
                                .map(TopicMapper::mapToEntity)
                                .orElse(null))
                        .build())
                .orElse(null);
    }

    public static SessionModel mapToModel(SessionEntity sessionEntity) {
        return Optional.ofNullable(sessionEntity)
                .map(entity -> SessionModel.builder()
                        .id(entity.getId())
                        .duration(entity.getDuration())
                        .topicModel(Optional.ofNullable(sessionEntity.getTopic())
                                .map(TopicMapper::mapToModel)
                                .orElse(null))
                        .createdAt(LocalDateTime.now())
                        .cpfList(entity.getCpfList())
                        .build())
                .orElse(null);
    }

}
