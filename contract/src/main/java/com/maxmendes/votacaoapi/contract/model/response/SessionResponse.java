package com.maxmendes.votacaoapi.contract.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionResponse {

    private String id;
    private Integer duration;
    private LocalDateTime createdAt;
    private List<TopicResponse> topicResponse;
    private List<String> cpfList;

}
