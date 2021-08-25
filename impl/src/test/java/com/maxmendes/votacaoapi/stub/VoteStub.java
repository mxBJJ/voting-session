package com.maxmendes.votacaoapi.stub;

import com.maxmendes.votacaoapi.impl.enums.VoteOptions;
import com.maxmendes.votacaoapi.impl.model.entity.VoteEntity;

import java.time.LocalDateTime;

public class VoteStub {

    public static VoteEntity createEntity() {
        return VoteEntity.builder()
                .cpf("53440803015")
                .sessionId("MONGOID")
                .vote(VoteOptions.SIM)
                .voteDateTime(LocalDateTime.of(2021,5,28,8,48,0))
                .build();
    }
}
