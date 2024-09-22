package com.crio.CoderHack.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crio.CoderHack.dto.User;

public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAllByOrderByScoreDesc();
}
