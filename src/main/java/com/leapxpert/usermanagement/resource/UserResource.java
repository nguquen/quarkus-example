package com.leapxpert.usermanagement.resource;

import com.leapxpert.usermanagement.service.UserService;
import com.leapxpert.usermanagement.service.dto.UserDto;
import io.smallrye.mutiny.Uni;
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

@Path("/v1/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@AllArgsConstructor
public class UserResource {

  private final UserService userService;

  @GET
  public Uni<Response> list() {
    return userService.findAll().map(result -> Response.ok(result).build());
  }

  @POST
  public Uni<Response> create(@NotNull @Valid UserDto userDto) {
    return userService.save(userDto).map(result -> Response.ok(result).build());
  }

  @PUT
  @Path("/{userId}")
  public Uni<Response> update(@PathParam("userId") String userId, @NotNull @Valid UserDto userDto) {
    userDto.setId(userId);
    return userService.update(userDto).map(result -> Response.ok(result).build());
  }
}
