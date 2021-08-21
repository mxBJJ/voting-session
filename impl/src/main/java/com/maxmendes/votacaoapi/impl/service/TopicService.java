package com.maxmendes.votacaoapi.impl.service;

import com.maxmendes.votacaoapi.impl.error.NotFoundException;
import com.maxmendes.votacaoapi.impl.exception.ExceptionMessageBuilder;
import com.maxmendes.votacaoapi.impl.mapper.TopicMapper;
import com.maxmendes.votacaoapi.impl.model.TopicModel;
import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;
import com.maxmendes.votacaoapi.impl.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class TopicService {

    private final SessionRepository sessionRepository;
    private final ExceptionMessageBuilder messageBuilder;

    public Mono<TopicModel> insertTopicInSession(TopicModel topicModel, String sessionId) {
        return sessionRepository.findById(sessionId)
                .filter(this::sessionAlreadyContainsTopic)
                .flatMap(sessionEntity -> sessionFactory(sessionEntity, topicModel))
                .flatMap(sessionRepository::save)
                .switchIfEmpty(Mono.error(new NotFoundException(messageBuilder.getSessionNotAvailable())))
                .map(sessionEntity -> TopicMapper.mapToModel(sessionEntity.getTopic()));
    }

    private Boolean sessionAlreadyContainsTopic(SessionEntity sessionEntity) {
        return ObjectUtils.isEmpty(sessionEntity.getTopic());
    }

    private Mono<SessionEntity> sessionFactory(SessionEntity sessionEntity, TopicModel topicModel) {
        sessionEntity.setTopic(TopicMapper.mapToEntity(topicModel));
        return Mono.just(sessionEntity);
    }

}
