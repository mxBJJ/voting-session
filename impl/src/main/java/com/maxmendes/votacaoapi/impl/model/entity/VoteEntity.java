package com.maxmendes.votacaoapi.impl.model.entity;

import com.maxmendes.votacaoapi.impl.enums.VoteOptions;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@Document(collection = "votos")
public class VoteEntity {

    private String cpf;
    private VoteOptions vote;
    private LocalDateTime voteDateTime;
    private String sessionId;

}
