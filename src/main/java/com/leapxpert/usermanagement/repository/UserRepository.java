package com.leapxpert.usermanagement.repository;

import com.leapxpert.usermanagement.repository.entity.UserEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepositoryBase<UserEntity, Integer> {

}
