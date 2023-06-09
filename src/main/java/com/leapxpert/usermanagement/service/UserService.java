package com.leapxpert.usermanagement.service;

import com.leapxpert.usermanagement.repository.UserRepository;
import com.leapxpert.usermanagement.service.dto.UserDto;
import com.leapxpert.usermanagement.service.exception.ServiceException;
import com.leapxpert.usermanagement.service.mapper.UserMapper;
import io.quarkus.hibernate.reactive.panache.common.runtime.ReactiveTransactional;
import io.smallrye.mutiny.Uni;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public Uni<List<UserDto>> findAll() {
    return userRepository.findAll().list().map(userMapper::entityToDomainList);
  }

  public Uni<UserDto> findById(@NonNull Integer userId) {
    return userRepository.findById(userId).map(userMapper::entityToDomain);
  }

  @ReactiveTransactional
  public Uni<UserDto> save(@Valid UserDto userDto) {
    userDto.setId(null);
    log.info("Saving User: {}", userDto);
    var entity = userMapper.toEntity(userDto);
    return userRepository.persistAndFlush(entity).map(userMapper::entityToDomain);
  }

  @ReactiveTransactional
  public Uni<UserDto> update(@Valid UserDto userDto) {
    log.info("Updating User: {}", userDto);
    if (Objects.isNull(userDto.getId())) {
      throw new ServiceException("User does not have a userId");
    }

    return userRepository.findById(userDto.getId()).flatMap(entity -> {
      if (entity == null) {
        throw new ServiceException("No User found for userId[%s]", userDto.getId());
      }
      userMapper.updateEntityFromDomain(userDto, entity);
      return userRepository.persistAndFlush(entity);
    }).map(userMapper::entityToDomain);
  }
}
