package com.leapxpert.usermanagement.service.mapper;

import com.leapxpert.usermanagement.grpc.User;
import com.leapxpert.usermanagement.repository.entity.UserEntity;
import com.leapxpert.usermanagement.service.dto.UserDto;
import java.util.List;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public interface UserMapper {

  List<UserDto> entityToDomainList(List<UserEntity> entities);

  UserDto entityToDomain(UserEntity entity);

  UserDto protoToDomain(User user);

  List<UserDto> protoToDomainList(List<User> users);

  UserEntity toEntity(UserDto domain);

  User toProto(UserDto domain);

  List<User> toProtoList(List<UserDto> domains);

  void updateEntityFromDomain(UserDto domain, @MappingTarget UserEntity entity);

  void updateDomainFromEntity(UserEntity entity, @MappingTarget UserDto domain);
}