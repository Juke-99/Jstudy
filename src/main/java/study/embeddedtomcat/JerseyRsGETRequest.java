package study.embeddedtomcat;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("helloGET")
public class JerseyRsGETRequest {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponse() {
		return Response.status(200).build();
	}
}
