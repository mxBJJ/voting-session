package com.maxmendes.votacaoapi.contract.mapper;

import com.maxmendes.votacaoapi.contract.model.request.TopicRequest;
import com.maxmendes.votacaoapi.contract.model.response.TopicResponse;
import com.maxmendes.votacaoapi.impl.model.TopicModel;

import java.util.Optional;

public class TopicMapper {

    public static TopicModel mapToModel(TopicRequest topicRequest) {
        return Optional.ofNullable(topicRequest)
                .map(request -> TopicModel.builder()
                        .question(request.getQuestion())
                        .build())
                .orElse(null);

    }

    public static TopicResponse mapToResponse(TopicModel topicModel) {
        return Optional.ofNullable(topicModel)
                .map(model -> TopicResponse.builder()
                        .question(model.getQuestion())
                        .build())
                .orElse(null);

    }
}
