package com.leapxpert.usermanagement.service.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class User {

  private Integer id;

  @NotEmpty
  private String firstName;

  @NotEmpty
  private String lastName;

  @Email
  private String email;

  private String phone;
}
