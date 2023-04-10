package com.leapxpert.usermanagement.repository;

import com.leapxpert.usermanagement.repository.entity.UserEntity;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheMongoRepository<UserEntity> {

}
