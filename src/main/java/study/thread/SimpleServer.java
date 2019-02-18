package study.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;

public class SimpleServer {
	private static final Logger logger = Logger.getLogger(SimpleServer.class.getName());
	static int port;
	
	public void start() throws IOException {
		InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), 0);
		ExecutorService executor = Executors.newCachedThreadPool(Util.threadFactory("SimpleServer", false));
		ServerSocket serverSocket = ServerSocketFactory.getDefault().createServerSocket();
		serverSocket.setReuseAddress(false);
		serverSocket.bind(inetSocketAddress, 10);
		
		port = serverSocket.getLocalPort();
		
		logger.info("Server Port " + port);
		
		executor.execute(new NamedRunnable("SimpleServer %s", port) {
			@Override
			protected void execute() {
				try {
					logger.info(SimpleServer.this + "start accept");
					accept();
				} catch (Throwable e) {
					logger.log(Level.WARNING, SimpleServer.this + " failed unexpectedly", e);
				}
			}
			
			private void accept() throws Exception {
				Socket socket;
				
				try {
					socket = serverSocket.accept();
				} catch (SocketException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@Override
	public String toString() {
		return "SimpleServer port: " + port + " => ";
	}
}
