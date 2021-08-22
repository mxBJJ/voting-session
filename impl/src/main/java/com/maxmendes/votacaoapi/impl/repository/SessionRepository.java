package com.maxmendes.votacaoapi.impl.repository;

import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;
import com.maxmendes.votacaoapi.impl.model.entity.VoteEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface SessionRepository extends ReactiveMongoRepository<SessionEntity, String> {

    Flux<VoteEntity> findVotesById(String id);
}
