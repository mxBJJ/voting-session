package com.maxmendes.votacaoapi.impl.model.entity;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Document(collection = "sessoes")
public class SessionEntity {

    @Id
    private String id;
    private Integer duration;
    private LocalDateTime createdAt;
    private TopicEntity topic;
    private List<VoteEntity> votes;
    private List<String> cpfList;

}
