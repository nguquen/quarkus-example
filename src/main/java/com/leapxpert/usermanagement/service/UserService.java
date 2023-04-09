package com.leapxpert.usermanagement.service;

import com.leapxpert.usermanagement.repository.UserRepository;
import com.leapxpert.usermanagement.service.dto.User;
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

@ApplicationScoped
@AllArgsConstructor
@Slf4j
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public List<User> findAll() {
    return this.userMapper.toDomainList(userRepository.findAll().list());
  }

  public Optional<User> findById(@NonNull Integer customerId) {
    return userRepository.findByIdOptional(customerId).map(userMapper::toDomain);
  }

  @Transactional
  public void save(@Valid User user) {
    log.debug("Saving User: {}", user);
    var entity = userMapper.toEntity(user);
    userRepository.persist(entity);
    userMapper.updateDomainFromEntity(entity, user);
  }

  @Transactional
  public void update(@Valid User user) {
    log.debug("Updating User: {}", user);
    if (Objects.isNull(user.getId())) {
      throw new ServiceException("User does not have a userId");
    }

    var entity = userRepository.findByIdOptional(user.getId())
        .orElseThrow(() -> new ServiceException("No User found for userId[%s]", user.getId()));

    userMapper.updateEntityFromDomain(user, entity);
    userRepository.persist(entity);
    userMapper.updateDomainFromEntity(entity, user);
  }
}
