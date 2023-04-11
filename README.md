## Branches

**master** / **postgres-reactive**: reactive paradigm with quarkus-hibernate-reactive-panache extension

**postgres**: imperative paradigm with quarkus-hibernate-orm-panache extension

**mongo-reactive**: reactive paradigm with quarkus-mongodb-panache extension

**mongo**: imperative paradigm with quarkus-mongodb-panache extension

## Stack
- reactive programming
- java: 17
- framework: quarkus 2
- database: postgresql / mongodb
- migration: flyway / liquibase
- kafka: https://quarkus.io/guides/kafka
- in-memory cache: https://quarkus.io/guides/cache
- unit-test: >= 60%
- load-test: gatling / k6. Can start with simple load-testing scenarios from day-1

## Structure

```
src
├── gatling
│   ├── java
│   │   ├── Engine.java
│   │   ├── IDEPathHelper.java
│   │   ├── Recorder.java
│   │   └── com
│   │       └── leapxpert
│   │           └── usermanagement
│   │               ├── HelloSimulation.java
│   │               └── UserManagementSimulation.java
│   └── resources
│       ├── gatling.conf
│       ├── logback.xml
│       └── recorder.conf
├── main
│   ├── docker
│   │   ├── Dockerfile.jvm
│   │   ├── Dockerfile.legacy-jar
│   │   ├── Dockerfile.native
│   │   └── Dockerfile.native-micro
│   ├── java
│   │   └── com
│   │       └── leapxpert
│   │           └── usermanagement
│   │               ├── FlywayMigration.java
│   │               ├── UserManagementService.java
│   │               ├── repository
│   │               │   ├── UserRepository.java
│   │               │   └── entity
│   │               │       └── UserEntity.java
│   │               ├── resource
│   │               │   ├── HelloResource.java
│   │               │   └── UserResource.java
│   │               └── service
│   │                   ├── UserService.java
│   │                   ├── dto
│   │                   │   └── UserDto.java
│   │                   ├── exception
│   │                   │   └── ServiceException.java
│   │                   └── mapper
│   │                       └── UserMapper.java
│   ├── proto
│   │   └── usermanagement.proto
│   └── resources
│       ├── META-INF
│       │   └── resources
│       │       └── index.html
│       ├── application.properties
│       └── db
│           └── migration
│               └── V1__user_table_create.sql
├── native-test
│   └── java
│       └── com
│           └── leapxpert
│               └── usermanagement
│                   └── resource
│                       └── HelloResourceIT.java
└── test
    └── java
        └── com
            └── leapxpert
                └── usermanagement
                    ├── resource
                    │   └── HelloResourceTest.java
                    └── service
                        └── UserServiceTest.java
```

**src/main/java**

- **/repository: repositories to access database
- **/repository/entity: database entities
- **/service: domain services
- **/service/dto: domain dtos
- **/service/exception: service exceptions
- **/service/mapper: mapper between dto <-> entity, dto <-> protobuf message
- **/resource: REST resources

**src/gatling**

- contains load-testing scenarios

**src/test**

- contains unit-tests

