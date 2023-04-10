package com.leapxpert.usermanagement.service;

import com.leapxpert.usermanagement.repository.UserRepository;
import com.leapxpert.usermanagement.service.dto.UserDto;
import com.leapxpert.usermanagement.service.exception.ServiceException;
import com.leapxpert.usermanagement.service.mapper.UserMapper;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<UserDto> findAll() {
    return this.userMapper.entityToDomainList(userRepository.findAll().list());
  }

  public Optional<UserDto> findById(@NonNull String userId) {
    return userRepository.findByIdOptional(new ObjectId(userId)).map(userMapper::entityToDomain);
  }

  @Transactional
  public void save(@Valid UserDto userDto) {
    log.info("Saving User: {}", userDto);
    userDto.setId(ObjectId.get().toHexString());
    var entity = userMapper.toEntity(userDto);
    userRepository.persist(entity);
    userMapper.updateDomainFromEntity(entity, userDto);
  }

  @Transactional
  public void update(@Valid UserDto userDto) {
    log.info("Updating User: {}", userDto);
    if (Objects.isNull(userDto.getId())) {
      throw new ServiceException("User does not have a userId");
    }

    var entity = userRepository.findByIdOptional(new ObjectId(userDto.getId()))
        .orElseThrow(() -> new ServiceException("No User found for userId[%s]", userDto.getId()));

    userMapper.updateEntityFromDomain(userDto, entity);
    userRepository.update(entity);
    userMapper.updateDomainFromEntity(entity, userDto);
  }
}
