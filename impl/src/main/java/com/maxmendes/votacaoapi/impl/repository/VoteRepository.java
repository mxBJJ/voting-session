package com.maxmendes.votacaoapi.impl.repository;

import com.maxmendes.votacaoapi.impl.model.entity.VoteEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends ReactiveMongoRepository<VoteEntity, String> {
}
