package de.rieckpil.user.boundary;

import de.rieckpil.user.entity.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.stream.JsonCollectors;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;

@Path("users")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

    @Inject
    UserStore userStore;

    @GET
    public JsonArray getUsers() {
        return userStore.getAllUser()
                .stream()
                .map(User::toJSON)
                .collect(JsonCollectors.toJsonArray());
    }

    @GET
    @Path("{id}")
    public JsonObject getUserById(@PathParam("id") long id) {
        return userStore.getUserById(id)
                .map(User::toJSON).get();
    }

    @POST
    public Response save(JsonObject input, @Context UriInfo info) {
        long userId = userStore.createUser(new User(input)).getId();
        URI uri = info.getAbsolutePathBuilder().
                path("/" + userId).
                build();
        return Response.created(uri).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveNewUser(@PathParam("id") int id, @Valid @NotNull User user) {
        return Response.ok().build();
    }
}
