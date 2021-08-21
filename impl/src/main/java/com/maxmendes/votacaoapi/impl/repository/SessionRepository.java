package com.maxmendes.votacaoapi.impl.repository;

import com.maxmendes.votacaoapi.impl.model.entity.SessionEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends ReactiveMongoRepository<SessionEntity, String> {
}
