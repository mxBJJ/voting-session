package com.maxmendes.votacaoapi.impl.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VoteOptions {
    SIM("SIM"),
    NAO("NÃO");

    private final String option;

}
