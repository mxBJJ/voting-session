package com.maxmendes.votacaoapi.impl.kafka.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class SessionKafkaModel {

    private String id;
    private Integer duration;
    private LocalDateTime createdAt;

}
