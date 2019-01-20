package study.embeddedtomcat;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.logging.Logger;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class JerseyRsGETRequestTest extends JerseyTest {
	Logger logger = Logger.getLogger("package study.embbedtomcat");
	
	@Override
    protected Application configure() {
        return new ResourceConfig(JerseyRsGETRequest.class);
    }
	
	@Test
	public void testGetResponse() {
		new HelloEmbeddedTomcat();
		Response res = target("/helloGET")
				.request()
				.get();
		
		String entity = res.readEntity(String.class);
		logger.info("Entity: " + entity);
		
		assertThat(res.getStatus(), is(200));
	}
}
