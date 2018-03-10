package de.rieckpil.user.boundary;

import de.rieckpil.user.entity.User;

import javax.activation.MimeType;
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

    @POST
    public Response save(JsonObject input, @Context UriInfo info) {
        long userId = userStore.createUser(new User(input)).getId();
        URI uri = info.getAbsolutePathBuilder().
                path("/" + userId).
                build();
        return Response.created(uri).build();
    }
}
