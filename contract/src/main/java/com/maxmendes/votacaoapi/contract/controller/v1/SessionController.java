package com.maxmendes.votacaoapi.contract.controller.v1;

import com.maxmendes.votacaoapi.contract.facade.SessionContractFacade;
import com.maxmendes.votacaoapi.contract.model.request.SessionRequest;
import com.maxmendes.votacaoapi.contract.model.response.SessionResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public Mono<SessionResponse> createSession(@RequestParam(required = false,
    defaultValue = "1") Integer duration) {
        return sessionContractFacade.openSession(SessionRequest.builder().duration(duration).build());
    }
}