package study.embeddedtomcat;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

public class EmbeddedServlet extends ServletContainer {
	private static final long serialVersionUID = 1L;
	
	EmbeddedServlet() {
		super(new EmbeddedConfig());
	}
	
	@ApplicationPath("rest")
	public static class EmbeddedConfig extends ResourceConfig {
		EmbeddedConfig() {
			packages(getClass().getPackage().getName());
		}
	}
}
