package com.leapxpert.usermanagement;

import static io.gatling.javaapi.core.CoreDsl.exec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.core.OpenInjectionStep.atOnceUsers;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ChainBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class HelloSimulation extends Simulation {

  HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");

  ChainBuilder getHello = exec(
      http("Hello").get("/v1/hello").check(status().is(200))
  );

  ScenarioBuilder scenario = scenario("HelloSimulation").exec(getHello);

  {
    setUp(
        scenario.injectOpen(atOnceUsers(10))
    ).protocols(httpProtocol);
  }
}
