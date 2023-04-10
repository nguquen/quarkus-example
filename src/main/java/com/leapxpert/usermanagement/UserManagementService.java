package com.leapxpert.usermanagement;

import com.leapxpert.usermanagement.grpc.CreateUserRequest;
import com.leapxpert.usermanagement.grpc.GetUsersRequest;
import com.leapxpert.usermanagement.grpc.UserManagementGrpc.UserManagementImplBase;
import com.leapxpert.usermanagement.grpc.UserResponse;
import com.leapxpert.usermanagement.grpc.UsersResponse;
import com.leapxpert.usermanagement.service.UserService;
import com.leapxpert.usermanagement.service.mapper.UserMapper;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import javax.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@GrpcService
@AllArgsConstructor
@Slf4j
public class UserManagementService extends UserManagementImplBase {

  private final UserService userService;
  private final UserMapper userMapper;


  @Override
  @Blocking
  public void getUsers(GetUsersRequest request, StreamObserver<UsersResponse> responseObserver) {
    var users = userService.findAll();
    log.info("getUsers invoked");

    responseObserver.onNext(UsersResponse.newBuilder()
        .addAllUsers(userMapper.toProtoList(users))
        .build());
    responseObserver.onCompleted();
  }

  @Override
  @Blocking
  public void createUser(CreateUserRequest request, StreamObserver<UserResponse> responseObserver) {
    try {
      var userDto = userMapper.protoToDomain(request.getUser());
      log.info("createUser invoked: {}", userDto);
      userService.save(userDto);

      responseObserver.onNext(UserResponse.newBuilder()
          .setUser(userMapper.toProto(userDto))
          .build());
    } catch (ConstraintViolationException e) {
      //TODO: handle exception
      log.error("createUser::error", e);
    } finally {
      responseObserver.onCompleted();
    }
  }
}
