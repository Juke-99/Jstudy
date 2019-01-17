package study.jersey.post.optionandhashmap;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class HashMapPOSTTest extends JerseyTest {
	
	@Override
	public Application configure() {
		return new ResourceConfig().register(HashMapPOST.class);
	}
	
	//@Override
    //public TestContainerFactory getTestContainerFactory() {
    //    return new GrizzlyWebTestContainerFactory();
    //}
	
	@Test
	public void testPostValue() {
		Logger log = Logger.getLogger("HashMapPOSTTest LOG");
		
		Form form1 = new Form();
		form1.param("value", "1");
		
		Form form2 = new Form();
		form2.param("value", null);
		
		Form form3 = new Form();
		form3.param("value", "");
		
		//Response getRes = target("/hashmap")
		//		.request()
		//		.get();
		
		Response res = target("/hashmap")
				.request()
				.header("Content-Type", MediaType.APPLICATION_FORM_URLENCODED)
				.post(Entity.form(form1));
		
		//post(Entity.entity(formParams, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		String str = res.readEntity(String.class);
		log.info("Entity : " + str);
		
		assertEquals(200, res.getStatus());
	}
	
	@Test
	public void testGetValue() {
		Logger log = Logger.getLogger("HashMapPOSTTest LOG");
		
		Response res = target("/hashmap")
				.request()
				.get();
		
		String str = res.readEntity(String.class);
		log.info("Entity : " + str);
		
		assertEquals(200, res.getStatus());
	}
}
