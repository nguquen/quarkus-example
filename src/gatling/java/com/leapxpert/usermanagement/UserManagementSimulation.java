package com.leapxpert.usermanagement;

import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.grpc;
import static com.github.phisgr.gatling.kt.grpc.GrpcDsl.statusCode;
import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;

import com.github.phisgr.gatling.kt.grpc.StaticGrpcProtocol;
import com.leapxpert.usermanagement.grpc.GetUsersRequest;
import com.leapxpert.usermanagement.grpc.UserManagementGrpc;
import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Status.Code;

public class UserManagementSimulation extends Simulation {

  StaticGrpcProtocol grpcProtocol = grpc(
      ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext()
  ).shareChannel();

  ChainBuilder getUsers = exec(
      grpc("GetUsers")
          .rpc(UserManagementGrpc.getGetUsersMethod())
          .payload(GetUsersRequest.newBuilder().build())
          .check(statusCode().is(Code.OK))
  );

  ScenarioBuilder scenario = scenario("UserManagementSimulation")
      .exec(getUsers);

  {
    setUp(
        scenario.injectOpen(atOnceUsers(1))
    ).protocols(grpcProtocol);
  }
}
