package com.leapxpert.usermanagement.service;

import com.leapxpert.usermanagement.repository.UserRepository;
import com.leapxpert.usermanagement.repository.entity.UserEntity;
import io.quarkus.hibernate.reactive.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import java.util.List;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
class UserServiceTest {

  @InjectMock
  private UserRepository userRepository;

  @Inject
  private UserService userService;

  @Test
  void listAll() {
    @SuppressWarnings("unchecked")
    var query = (PanacheQuery<UserEntity>) Mockito.mock(PanacheQuery.class);

    Mockito.when(query.list())
        .thenReturn(Uni.createFrom().item(List.of(Mockito.mock(UserEntity.class))));
    Mockito.when(userRepository.findAll()).thenReturn(query);

    var usersUni = userService.findAll();

    var subscriber = usersUni
        .invoke(userDtos -> Assertions.assertEquals(1, userDtos.size()))
        .subscribe().withSubscriber(UniAssertSubscriber.create());

    subscriber.assertCompleted();
  }

}
