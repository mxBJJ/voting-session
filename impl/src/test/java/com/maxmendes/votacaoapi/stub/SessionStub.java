package com.maxmendes.votacaoapi.stub;

import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;

import java.time.LocalDateTime;
import java.util.Collections;

public class SessionStub {

    public static SessionEntity createEntity() {
        return SessionEntity.builder()
                .cpfList(Collections.singletonList("53440803015"))
                .createdAt(LocalDateTime.of(2021,5,28,8,48,0))
                .duration(2)
                .id("MONGOID")
                .topic(TopicStub.createEntity())
                .votes(Collections.singletonList(VoteStub.createEntity()))
                .build();
    }

    public static SessionEntity createEntityWithoutTopic() {
        return SessionEntity.builder()
                .cpfList(Collections.singletonList("53440803015"))
                .createdAt(LocalDateTime.of(2021,5,28,8,48,0))
                .duration(2)
                .id("MONGOID")
                .votes(Collections.singletonList(VoteStub.createEntity()))
                .build();
    }
}
