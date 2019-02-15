package study.thread;

import java.io.IOException;

import org.junit.Test;

public class SimpleServerTest {

	@Test
	public void startTest() throws IOException {
		SimpleServer server = new SimpleServer();
		server.start();
	}
}
