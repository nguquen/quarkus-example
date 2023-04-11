package com.leapxpert.usermanagement.repository;

import com.leapxpert.usermanagement.repository.entity.UserEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements ReactivePanacheMongoRepository<UserEntity> {

}
