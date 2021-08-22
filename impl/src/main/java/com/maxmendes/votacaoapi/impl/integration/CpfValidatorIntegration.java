package com.maxmendes.votacaoapi.impl.integration;

import com.maxmendes.votacaoapi.impl.error.NotFoundException;
import com.maxmendes.votacaoapi.impl.exception.ExceptionMessageBuilder;
import com.maxmendes.votacaoapi.impl.integration.response.CpfValidatorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CpfValidatorIntegration {

    private final ExceptionMessageBuilder messageBuilder;
    private final WebClient webClient;
    private final String baseUrl;

    public CpfValidatorIntegration(
            @Value("${cpf.validator.baseUrl}") String baseUrl,
            ExceptionMessageBuilder messageBuilder) {
        this.messageBuilder = messageBuilder;
        this.webClient = WebClient.create(baseUrl);
        this.baseUrl = baseUrl;
    }

    public Mono<CpfValidatorResponse> validateCpf(String cpf) {
        var uri = UriComponentsBuilder.fromUriString(baseUrl)
                .pathSegment(cpf)
                .toUriString();
        return webClient.get()
                .uri(uri)
                .retrieve()
                .onStatus(HttpStatus.NOT_FOUND::equals, clientResponse -> Mono
                        .error(new NotFoundException(messageBuilder.getInvalidCpf())))
                .bodyToMono(CpfValidatorResponse.class)
                .doOnError(throwable -> log.error("INTEGRATION CALL ERROR: {}", uri))
                .doOnSuccess(s -> log.info("CPF VALIDATION SUCCESSFUL: {}", uri));
    }
}
