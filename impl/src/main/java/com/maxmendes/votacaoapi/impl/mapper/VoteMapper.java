package com.maxmendes.votacaoapi.impl.mapper;

import com.maxmendes.votacaoapi.impl.model.VoteModel;
import com.maxmendes.votacaoapi.impl.model.entity.VoteEntity;

import java.time.LocalDateTime;
import java.util.Optional;

public class VoteMapper {

    public static VoteEntity mapToEntity(VoteModel voteModel) {
        return Optional.ofNullable(voteModel)
                .map(model -> VoteEntity.builder()
                        .vote(model.getVote())
                        .cpf(model.getCpf())
                        .voteDateTime(LocalDateTime.now())
                        .build())
                .orElse(null);
    }

    public static VoteModel mapToModel(VoteEntity voteEntity) {
        return Optional.ofNullable(voteEntity)
                .map(entity -> VoteModel.builder()
                        .vote(entity.getVote())
                        .cpf(entity.getCpf())
                        .voteDateTime(entity.getVoteDateTime())
                        .build())
                .orElse(null);
    }
}
