package com.maxmendes.votacaoapi.contract.facade;

import com.maxmendes.votacaoapi.contract.mapper.TopicMapper;
import com.maxmendes.votacaoapi.contract.model.request.TopicRequest;
import com.maxmendes.votacaoapi.contract.model.response.TopicResponse;
import com.maxmendes.votacaoapi.impl.facade.TopicFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TopicContractFacade {

    private final TopicFacade topicFacade;

    public Mono<TopicResponse> insertTopicOnSession(TopicRequest topicRequest, String sessionId) {
        return topicFacade.insertTopicInSession(TopicMapper.mapToModel(topicRequest), sessionId)
                .map(TopicMapper::mapToResponse);
    }
}
