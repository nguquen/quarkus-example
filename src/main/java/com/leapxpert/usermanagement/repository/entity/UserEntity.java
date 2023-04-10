package com.leapxpert.usermanagement.repository.entity;

import io.quarkus.mongodb.panache.common.MongoEntity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.bson.types.ObjectId;

@MongoEntity(collection = "User")
@Data
public class UserEntity {

  private ObjectId id;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @Email
  private String email;

  private String phone;
}
