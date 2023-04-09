package com.leapxpert.usermanagement.resource;

import com.leapxpert.usermanagement.service.UserService;
import com.leapxpert.usermanagement.service.dto.UserDto;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
@Slf4j
public class UserResource {

  private final UserService userService;


  @GET
  public Response list() {
    return Response.ok(userService.findAll()).build();
  }

  @POST
  public Response create(@NotNull @Valid UserDto userDto) {
    userService.save(userDto);
    return Response.ok(userDto).build();
  }

  @PUT
  @Path("/{userId}")
  public Response update(@PathParam("userId") Integer userId, @NotNull @Valid UserDto userDto) {
    userDto.setId(userId);
    userService.update(userDto);
    return Response.ok(userDto).build();
  }
}