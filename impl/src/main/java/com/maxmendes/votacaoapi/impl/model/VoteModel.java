package com.maxmendes.votacaoapi.impl.model;

import com.maxmendes.votacaoapi.impl.enums.VoteOptions;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class VoteModel {

    private String cpf;
    private VoteOptions vote;
    private LocalDateTime voteDateTime;

}
