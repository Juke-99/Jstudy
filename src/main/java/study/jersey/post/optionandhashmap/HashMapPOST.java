package study.jersey.post.optionandhashmap;

import java.util.HashMap;
import java.util.Optional;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/hashmap")
public class HashMapPOST {
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response postValue(@FormParam("value") String value) {
		HashMap<String, String> hashmap = new HashMap<>();
		hashmap.put("enterValue", value);
		
		Optional<String> valOpt = Optional.ofNullable(hashmap.get("enterValue"));
		
		if(valOpt.isPresent()) {
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
		
		return Response.status(Response.Status.OK).build();
	}
}
