package com.maxmendes.votacaoapi.impl.facade;

import com.maxmendes.votacaoapi.impl.model.TopicModel;
import com.maxmendes.votacaoapi.impl.service.TopicService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class TopicFacade {

    private final TopicService topicService;

    public Mono<TopicModel> insertTopicInSession(TopicModel topicModel, String sessionId){
        return topicService.insertTopicInSession(topicModel, sessionId);
    }

}
