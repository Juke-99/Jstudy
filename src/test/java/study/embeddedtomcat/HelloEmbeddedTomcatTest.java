package study.embeddedtomcat;

import java.util.logging.Logger;

import org.apache.catalina.LifecycleException;
import org.junit.Test;

public class HelloEmbeddedTomcatTest {
	Logger logger = Logger.getLogger("package study.embbedtomcat.HelloEmbeddedTomcatTest");
	
	@Test
	public void testStartTomcat() {
		try {
			logger.info("stop Tomcat");
			HelloEmbeddedTomcat.tomcat.stop();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}
	}
}
