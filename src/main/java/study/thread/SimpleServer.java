package study.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;

public class SimpleServer {
	private static final Logger logger = Logger.getLogger(SimpleServer.class.getName());
	
	public void start() throws IOException {
		InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), 0);
		ExecutorService executor = Executors.newCachedThreadPool(Util.threadFactory("SimpleServer", false));
		ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket();
		
		logger.info("Server Port " + inetSocketAddress.getPort());
		
		serverSocket.setReuseAddress(false);
		serverSocket.bind(inetSocketAddress, 10);
		
		executor.execute(new NamedRunnable());
	}
}
