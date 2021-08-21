package com.maxmendes.votacaoapi.contract.controller.v1;

import com.maxmendes.votacaoapi.contract.exception.GlobalExceptionHandler;
import com.maxmendes.votacaoapi.contract.facade.SessionContractFacade;
import com.maxmendes.votacaoapi.contract.model.request.SessionRequest;
import com.maxmendes.votacaoapi.contract.model.request.VoteRequest;
import com.maxmendes.votacaoapi.contract.model.response.SessionResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/session")
@AllArgsConstructor
public class SessionController {

    private final SessionContractFacade sessionContractFacade;

    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "PRE_CONDITION_FAILED"),
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 412, message = "INTERNAL_SERVER_ERROR")
    })
    public Mono<SessionResponse> createSession(@Validated @RequestBody SessionRequest sessionRequest) {
        return sessionContractFacade.openSession(sessionRequest);
    }

}