package study.embeddedtomcat;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.servlets.DefaultServlet;
import org.apache.catalina.startup.Tomcat;

public class HelloEmbeddedTomcat {
	
	Logger logger = Logger.getLogger("package study.embbedtomcat");
	static Tomcat tomcat = new Tomcat();
	
	HelloEmbeddedTomcat() {
		try {
			logger.info("start Tomcat");
			startTomcat();
		} catch (LifecycleException e) {
			logger.warning(e.toString());
		}
	}
	
	public void startTomcat() throws LifecycleException {
		tomcat.setPort(8080);
		
		File base = new File("src/main/java");
		Context context = tomcat.addContext("/", base.getAbsolutePath());
		
		//Tomcat.addServlet(context, "default", new DefaultServlet()).addMapping("/");
		Tomcat.addServlet(context, "hello", new HttpServlet() {
			private static final long serialVersionUID = 1L;

			protected void service(HttpServletRequest req, HttpServletResponse res) {
				Writer w;
				try {
					w = res.getWriter();
					w.write("Hello world!");
					w.flush();
				} catch (IOException e) {
					logger.warning(e.toString());
				}
			}
		}).addMapping("hello");
		
		Tomcat.addServlet(context, "embGET", new EmbeddedServlet()).addMapping("helloGET");
		
		tomcat.start();
	}
}
