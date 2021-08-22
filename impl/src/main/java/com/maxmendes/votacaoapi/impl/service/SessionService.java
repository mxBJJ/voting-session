package com.maxmendes.votacaoapi.impl.service;

import com.maxmendes.votacaoapi.impl.error.NotFoundException;
import com.maxmendes.votacaoapi.impl.exception.ExceptionMessageBuilder;
import com.maxmendes.votacaoapi.impl.kafka.producer.SessionKafkaProducer;
import com.maxmendes.votacaoapi.impl.mapper.SessionMapper;
import com.maxmendes.votacaoapi.impl.model.SessionModel;
import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;
import com.maxmendes.votacaoapi.impl.repository.SessionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@AllArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;
    private final ExceptionMessageBuilder messageBuilder;
    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);
    private final SessionKafkaProducer sessionKafkaProducer;

    public Mono<SessionModel> openSession(SessionModel sessionModel) {
        return sessionRepository.save(SessionMapper.mapToEntity(sessionModel))
                .flatMap(sessionEntity -> {
                    schedule(sessionEntity);
                    return Mono.just(sessionEntity);
                })
                .map(SessionMapper::mapToModel);
    }

    public Mono<SessionEntity> findSessionById(String id) {
        return sessionRepository.findById(id)
                .switchIfEmpty(Mono.error(new NotFoundException(messageBuilder.getNotFound())));
    }

    public Mono<SessionEntity> save(SessionEntity sessionEntity) {
        return sessionRepository.save(sessionEntity);
    }

    private void schedule(SessionEntity sessionEntity) {
        executor.schedule(new Runnable() {
            @Override
            public void run() {
                publishKafka(sessionEntity);
            }
        }, sessionEntity.getDuration(), TimeUnit.SECONDS);
    }

    private void publishKafka(SessionEntity sessionEntity) {
        sessionKafkaProducer.publish(SessionMapper.mapToKafkaEntity(sessionEntity));
    }

}
