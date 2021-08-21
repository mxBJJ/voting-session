package com.maxmendes.votacaoapi.impl.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SessionModel {

    private String id;
    private Integer duration;
    private LocalDateTime createdAt;
    private TopicModel topicModel;
    private List<String> cpfList;

}
