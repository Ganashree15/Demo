package resource.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class UserNotFoundException extends WebApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		this("User not found", null);

	}

	public UserNotFoundException(String msg, String desc) {

		super(Response.status(Status.NOT_FOUND).entity(new ExceptionInfo(Status.NOT_FOUND.getStatusCode(), msg, desc))
				.type(MediaType.APPLICATION_JSON_TYPE).build());

	}

}
