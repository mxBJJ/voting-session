package com.maxmendes.votacaoapi.stub;

import com.maxmendes.votacaoapi.impl.model.TopicModel;
import com.maxmendes.votacaoapi.impl.model.entity.TopicEntity;

import java.time.LocalDateTime;

public class TopicStub {

    public static TopicEntity createEntity() {
        return TopicEntity.builder()
                .question("Você a prova ...?")
                .createdAt(LocalDateTime.of(2021, 5, 28, 8, 48, 0))
                .build();
    }

    public static TopicModel createModel() {
        return TopicModel.builder()
                .question("Você a prova ...?")
                .build();
    }
}
