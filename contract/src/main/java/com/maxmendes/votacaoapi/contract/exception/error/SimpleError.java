package com.maxmendes.votacaoapi.contract.exception.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SimpleError {

    private Integer statusCode;
    private String description;
    private Long timestamp;
    private String path;

}
