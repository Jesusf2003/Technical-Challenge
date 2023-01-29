package com.challenge.domain;

import java.util.List;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UsersRepository extends ReactiveCrudRepository<Users, Long> {

	Users findByUsername(String username);
}