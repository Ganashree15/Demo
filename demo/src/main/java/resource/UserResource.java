package resource;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.User;
import resource.exception.UserNotFoundException;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("user")

public class UserResource {

	private Map<Long, User> userMap = new HashMap<Long, User>();

	public UserResource() {
		User user = new User();
		user.setId(0L);
		user.setName("user0");
		userMap.put(user.getId(), user);

		User user1 = new User();
		user1.setId(1L);
		user1.setName("user1");
		userMap.put(user1.getId(), user1);

	}

	@GET
	public Collection<User> listUsers() {
		return userMap.values();

	}

	@GET
	@Path("/{userId}")
	public User getUser(@PathParam("userId") long userId) {

		User user = userMap.get(userId);
		if (user == null) {

			throw new UserNotFoundException("User '" + userId + "'is not found", null);
		}
		return user;
	}
}
