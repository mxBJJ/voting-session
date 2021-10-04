package com.maxmendes.votacaoapi.impl.mapper;

import com.maxmendes.votacaoapi.impl.clock.TimeMachine;
import com.maxmendes.votacaoapi.impl.model.TopicModel;
import com.maxmendes.votacaoapi.impl.model.entity.TopicEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TopicMapper {

    public static TopicEntity mapToEntity(TopicModel topicModel) {
        return Optional.ofNullable(topicModel)
                .map(model -> TopicEntity.builder()
                        .question(model.getQuestion())
                        .createdAt(TimeMachine.now())
                        .build())
                .orElse(null);
    }

    public static List<TopicEntity> mapToEntityList(List<TopicModel> topicModels) {
        return topicModels.stream()
                .map(TopicMapper::mapToEntity)
                .collect(Collectors.toList());

    }

    public static TopicModel mapToModel(TopicEntity topicEntity) {
        return Optional.ofNullable(topicEntity)
                .map(entity -> TopicModel.builder()
                        .question(entity.getQuestion())
                        .build())
                .orElse(null);

    }

    public static List<TopicModel> mapToModelList(List<TopicEntity> topicEntity) {
        return topicEntity.stream()
                .map(TopicMapper::mapToModel)
                .collect(Collectors.toList());

    }

}
