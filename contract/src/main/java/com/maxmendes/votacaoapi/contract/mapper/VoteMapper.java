package com.maxmendes.votacaoapi.contract.mapper;

import com.maxmendes.votacaoapi.contract.model.request.VoteRequest;
import com.maxmendes.votacaoapi.impl.model.VoteModel;

import java.util.Optional;

public class VoteMapper {

    public static VoteModel mapToModel(VoteRequest voteRequest) {
        return Optional.ofNullable(voteRequest)
                .map(vote -> VoteModel.builder()
                        .vote(vote.getVote())
                        .cpf(vote.getCpf())
                        .build())
                .orElse(null);
    }
}
