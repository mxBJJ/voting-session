package com.maxmendes.votacaoapi.contract.controller.v1;

import com.maxmendes.votacaoapi.contract.exception.GlobalExceptionHandler;
import com.maxmendes.votacaoapi.contract.facade.TopicContractFacade;
import com.maxmendes.votacaoapi.contract.model.request.TopicRequest;
import com.maxmendes.votacaoapi.contract.model.request.VoteRequest;
import com.maxmendes.votacaoapi.contract.model.response.TopicResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/v1/topic")
@AllArgsConstructor
public class TopicController {

    private final TopicContractFacade topicContractFacade;

    @PostMapping("/{sessionId}")
    @ExceptionHandler(GlobalExceptionHandler.class)
    @ApiResponses(value = {
            @ApiResponse(code = 412, message = "PRE_CONDITION_FAILED"),
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "NOT_FOUND"),
            @ApiResponse(code = 412, message = "INTERNAL_SERVER_ERROR")
    })
    public Mono<TopicResponse> vote(
            @RequestBody TopicRequest topicRequest,
            @PathVariable String sessionId) {
        log.info("Iniciando votacao...");
        return topicContractFacade.insertTopicOnSession(topicRequest, sessionId);
    }
}
