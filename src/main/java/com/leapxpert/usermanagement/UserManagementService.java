package com.leapxpert.usermanagement;

import com.leapxpert.usermanagement.grpc.CreateUserRequest;
import com.leapxpert.usermanagement.grpc.GetUsersRequest;
import com.leapxpert.usermanagement.grpc.UserManagement;
import com.leapxpert.usermanagement.grpc.UserResponse;
import com.leapxpert.usermanagement.grpc.UsersResponse;
import com.leapxpert.usermanagement.service.UserService;
import com.leapxpert.usermanagement.service.mapper.UserMapper;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import javax.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@GrpcService
@AllArgsConstructor
@Slf4j
public class UserManagementService implements UserManagement {

  private final UserService userService;
  private final UserMapper userMapper;

  @Override
  public Uni<UsersResponse> getUsers(GetUsersRequest request) {
    log.info("getUsers invoked");

    return userService.findAll().map(
        users -> UsersResponse.newBuilder().addAllUsers(userMapper.toProtoList(users)).build());
  }

  @Override
  public Uni<UserResponse> createUser(CreateUserRequest request) {
    try {
      var userDto = userMapper.protoToDomain(request.getUser());
      log.info("createUser invoked: {}", userDto);

      return userService.save(userDto)
          .map(user -> UserResponse.newBuilder().setUser(userMapper.toProto(user)).build());
    } catch (ConstraintViolationException e) {
      log.error("createUser::error", e);
      //TODO: handle exception
      return Uni.createFrom().item(() -> UserResponse.newBuilder().build());
    }
  }
}
