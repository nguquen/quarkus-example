package com.leapxpert.usermanagement;

import com.leapxpert.usermanagement.grpc.GetUsersRequest;
import com.leapxpert.usermanagement.grpc.UserManagementGrpc.UserManagementImplBase;
import com.leapxpert.usermanagement.grpc.UsersResponse;
import com.leapxpert.usermanagement.service.UserService;
import com.leapxpert.usermanagement.service.mapper.UserMapper;
import io.grpc.stub.StreamObserver;
import io.quarkus.grpc.GrpcService;
import io.smallrye.common.annotation.Blocking;
import lombok.AllArgsConstructor;

@GrpcService
@AllArgsConstructor
public class UserManagementService extends UserManagementImplBase {

  private final UserService userService;
  private final UserMapper userMapper;


  @Override
  @Blocking
  public void getUsers(GetUsersRequest request, StreamObserver<UsersResponse> responseObserver) {
    var users = userService.findAll();

    responseObserver.onNext(UsersResponse.newBuilder()
        .addAllUsers(userMapper.toProtoList(users))
        .build());
    responseObserver.onCompleted();
  }
}
