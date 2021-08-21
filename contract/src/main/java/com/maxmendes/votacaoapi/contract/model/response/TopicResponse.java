package com.maxmendes.votacaoapi.contract.model.response;

import lombok.Builder;
import lombok.Data;
import org.apache.kafka.common.requests.VoteResponse;

import java.util.List;

@Data
@Builder
public class TopicResponse {

    private String question;

}
