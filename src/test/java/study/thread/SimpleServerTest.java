package study.thread;

import java.io.IOException;

import org.junit.After;
import org.junit.Test;

public class SimpleServerTest {
    SimpleServer server = new SimpleServer();
    
    @After
    public void complete() throws IOException {
        server.shutdown();
    }

	@Test
	public void startTest() throws IOException {
	    server.start();
	}
}
