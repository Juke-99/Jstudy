package study.jersey.post.optionandhashmap;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.logging.Logger;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.spi.TestContainerFactory;
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
		
		Response res = target("/hashmap")
				.request()
				.post(Entity.form(form1));
		
		//post(Entity.entity(formParams, MediaType.APPLICATION_FORM_URLENCODED_TYPE), String.class);
		
		//String str = res.readEntity(String.class);
		//log.info(str);
		
		assertThat(res.getStatus(), equalTo(200));
	}
}
