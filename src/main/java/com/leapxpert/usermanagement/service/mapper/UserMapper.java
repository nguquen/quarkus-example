package com.leapxpert.usermanagement.service.mapper;

import com.leapxpert.usermanagement.repository.entity.UserEntity;
import com.leapxpert.usermanagement.service.dto.User;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface UserMapper {

  List<User> toDomainList(List<UserEntity> entities);

  User toDomain(UserEntity entity);

  @InheritInverseConfiguration(name = "toDomain")
  UserEntity toEntity(User domain);

  void updateEntityFromDomain(User domain, @MappingTarget UserEntity entity);

  void updateDomainFromEntity(UserEntity entity, @MappingTarget User domain);
}