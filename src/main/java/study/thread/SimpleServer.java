package study.thread;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ServerSocketFactory;

import org.junit.rules.ExternalResource;

public final class SimpleServer extends ExternalResource implements Closeable {
    private final Set<Socket> openClientSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    private static final Logger logger = Logger.getLogger(SimpleServer.class.getName());
    static int port;
    private ExecutorService executor;
    private ServerSocketFactory socketFactory;
    private ServerSocket serverSocket;
    private boolean started;
    
    @Override
    protected synchronized void before() {
        if (started) return;
        try {
            start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    protected synchronized void after() {
        try {
            shutdown();
        } catch (IOException e) {
            logger.log(Level.WARNING, "MockWebServer shutdown failed", e);
        }
    }
    
    public void start() throws IOException {
        start(0);
    }
    
    public void start(int port) throws IOException {
        start(InetAddress.getByName("localhost"), port);
    }
    
    public void start(InetAddress address, int port) throws IOException {
        start(new InetSocketAddress(address, port));
    }
    
    public synchronized void start(InetSocketAddress inetSocketAddress) throws IOException {
        if (started) throw new IllegalStateException("start() already called");
        started = true;
        
        executor = Executors.newCachedThreadPool(Util.threadFactory("SimpleServer", false));
        
        socketFactory = Optional.ofNullable(socketFactory).orElse(ServerSocketFactory.getDefault());
        serverSocket = socketFactory.createServerSocket();
        serverSocket.setReuseAddress(inetSocketAddress.getPort() != 0);
        serverSocket.bind(inetSocketAddress, 50);
        
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
        if (!started) return;
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

    @Override
    public void close() throws IOException {
        shutdown();        
    }
}
