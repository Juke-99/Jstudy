package study.thread;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;

public class SimpleServer {
    private final Set<Socket> openClientSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static final Logger logger = Logger.getLogger(SimpleServer.class.getName());
    static int port;
    private ExecutorService executor;
    private ServerSocket serverSocket;
    private boolean started;
    
    public synchronized void start() throws IOException {
        if (started) throw new IllegalStateException("start() already called");
        started = true;
        
        InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getByName("localhost"), 0);
        executor = Executors.newCachedThreadPool(Util.threadFactory("SimpleServer", false));
        serverSocket = ServerSocketFactory.getDefault().createServerSocket();
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
                
                Util.closeQuietly(serverSocket);
                
                for (Iterator<Socket> s = openClientSockets.iterator(); s.hasNext(); ) {
                    Util.closeQuietly(s.next());
                    s.remove();
                }
            
                executor.shutdown();
            }
            	
            private void accept() throws Exception {
                while(true) {
                    Socket socket;
                    	
                    try {
                        socket = serverSocket.accept();
                    } catch (SocketException e) {
                        e.printStackTrace();
                        return;
                    }
                    
                    openClientSockets.add(socket);
                    otherConnection(socket);
                }
            }
        });
    }
    
    private void otherConnection(final Socket remoteSocket) {
        executor.execute(new NamedRunnable("SimpleServer %s", remoteSocket.getRemoteSocketAddress()) {
            @Override
            protected void execute() {
                try {
                    logger.info(SimpleServer.this + "start accept");
                    connection();
                } catch (Throwable e) {
                    logger.log(Level.WARNING, SimpleServer.this + " failed unexpectedly", e);
                }
            }
                
            private void connection() throws Exception {
                Socket socket = remoteSocket;
                socket.close();
                openClientSockets.remove(socket);
            }
        });
    }
    
    public synchronized void shutdown() throws IOException {
        if(!started) return;
        if (serverSocket == null) throw new IllegalStateException("shutdown() before start()");
        
        logger.info("Server shutdown...");
        serverSocket.close();
        
        try {
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
              throw new IOException("Gave up waiting for executor to shut down");
            }
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
    }
    
    @Override
    public String toString() {
        return "SimpleServer port: " + port + " => ";
    }
}
