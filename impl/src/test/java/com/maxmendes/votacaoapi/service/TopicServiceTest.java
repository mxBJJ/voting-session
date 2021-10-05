package com.maxmendes.votacaoapi.service;

import com.maxmendes.votacaoapi.impl.clock.TimeMachine;
import com.maxmendes.votacaoapi.impl.error.NotFoundException;
import com.maxmendes.votacaoapi.impl.exception.ExceptionMessageBuilder;
import com.maxmendes.votacaoapi.impl.repository.SessionRepository;
import com.maxmendes.votacaoapi.impl.service.TopicService;
import com.maxmendes.votacaoapi.stub.SessionStub;
import com.maxmendes.votacaoapi.stub.TopicStub;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TopicServiceTest {

    @Mock
    SessionRepository sessionRepository;

    @Mock
    ExceptionMessageBuilder exceptionMessageBuilder;

    @InjectMocks
    TopicService topicService;

    @Test
    void shouldInsertTopicWithSuccess() {

        when(sessionRepository.findById("MONGOID"))
                .thenReturn(Mono.just(SessionStub.createEntityWithoutTopic()));
        when(sessionRepository.save(SessionStub.createEntity()))
                .thenReturn(Mono.just(SessionStub.createEntity()));
        when(exceptionMessageBuilder.getSessionNotAvailable())
                .thenReturn("error.sessionNotAvailable");

        LocalDateTime dateTime = LocalDateTime
                .of(2021, 5, 28, 8, 48, 0);

        TimeMachine.useFixedClockAt(dateTime);

        StepVerifier.create(topicService
                .insertTopicInSession(TopicStub.createModel(), "MONGOID"))
                .expectNext(TopicStub.createModel())
                .verifyComplete();
    }

    @Test
    void shouldThrowNotFoundExceptionWhenInsertTopic() {

        when(sessionRepository.findById("MONGOID"))
                .thenReturn(Mono.just(SessionStub.createEntity()));
        when(exceptionMessageBuilder.getSessionNotAvailable())
                .thenReturn("error.sessionNotAvailable");

        LocalDateTime dateTime = LocalDateTime
                .of(2021, 5, 28, 8, 48, 0);

        TimeMachine.useFixedClockAt(dateTime);

        StepVerifier.create(topicService
                .insertTopicInSession(TopicStub.createModel(), "MONGOID"))
                .expectError(NotFoundException.class)
                .verify();
    }

}
