package com.maxmendes.votacaoapi.impl.exception;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class ExceptionMessageBuilder {

    @Value("${error.NotFound}")
    private String notFound;

    @Value("${error.AlreadyVote}")
    private String alreadyVote;

    @Value("${error.sessionNotAvailable}")
    private String sessionNotAvailable;

}
