package com.leapxpert.usermanagement;

import io.quarkus.runtime.StartupEvent;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.flywaydb.core.Flyway;

@ApplicationScoped
public class FlywayMigration {

  @ConfigProperty(name = "quarkus.flyway.migrate-at-start")
  boolean runMigration;

  @ConfigProperty(name = "quarkus.datasource.reactive.url")
  String datasourceUrl;

  @ConfigProperty(name = "quarkus.datasource.username")
  String datasourceUsername;

  @ConfigProperty(name = "quarkus.datasource.password")
  String datasourcePassword;

  public void runFlywayMigration(@Observes StartupEvent event) {
    if (!runMigration) {
      return;
    }

    Flyway flyway = Flyway.configure()
        .dataSource(datasourceUrl.replace("vertx-reactive", "jdbc"), datasourceUsername,
            datasourcePassword).load();
    flyway.migrate();
  }
}
