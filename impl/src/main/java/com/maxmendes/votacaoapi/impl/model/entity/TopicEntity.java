package com.maxmendes.votacaoapi.impl.model.entity;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class TopicEntity {

    private String question;
    private LocalDateTime createdAt;

}
