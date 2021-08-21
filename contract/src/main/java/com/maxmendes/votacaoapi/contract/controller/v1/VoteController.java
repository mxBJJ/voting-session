package com.maxmendes.votacaoapi.contract.controller.v1;

import com.maxmendes.votacaoapi.contract.exception.GlobalExceptionHandler;
import com.maxmendes.votacaoapi.contract.facade.VoteContractFacade;
import com.maxmendes.votacaoapi.contract.model.request.VoteRequest;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/vote")
@AllArgsConstructor
public class VoteController {

    private final VoteContractFacade voteContractFacade;

    @PostMapping("/{sessionId}")
    @ExceptionHandler(GlobalExceptionHandler.class)
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "PRE_CONDITION_FAILED"),
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 412, message = "INTERNAL_SERVER_ERROR")
    })
    public Mono<Void> vote(
            @RequestBody VoteRequest voteRequest,
            @PathVariable String sessionId) {
        log.info("Iniciando votacao...");
        return voteContractFacade.vote(voteRequest, sessionId);
    }
}
