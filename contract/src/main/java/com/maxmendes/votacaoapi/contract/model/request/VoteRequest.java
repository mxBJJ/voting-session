package com.maxmendes.votacaoapi.contract.model.request;

import com.maxmendes.votacaoapi.impl.enums.VoteOptions;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VoteRequest {

    private String cpf;
    private VoteOptions vote;

}
