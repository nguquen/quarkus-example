package com.leapxpert.usermanagement.service.mapper;

import com.leapxpert.usermanagement.grpc.User;
import com.leapxpert.usermanagement.repository.entity.UserEntity;
import com.leapxpert.usermanagement.service.dto.UserDto;
import java.util.List;
import org.bson.types.ObjectId;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi", collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED)
public abstract class UserMapper {

  public abstract List<UserDto> entityToDomainList(List<UserEntity> entities);

  public abstract UserDto entityToDomain(UserEntity entity);

  public abstract UserDto protoToDomain(User user);

  public abstract List<UserDto> protoToDomainList(List<User> users);

  public abstract UserEntity toEntity(UserDto domain);

  public abstract User toProto(UserDto domain);

  public abstract List<User> toProtoList(List<UserDto> domains);

  public abstract void updateEntityFromDomain(UserDto domain, @MappingTarget UserEntity entity);

  public abstract void updateDomainFromEntity(UserEntity entity, @MappingTarget UserDto domain);

  public ObjectId mapId(String id) {
    return new ObjectId(id);
  }

  public String mapId(ObjectId id) {
    return id.toHexString();
  }
}