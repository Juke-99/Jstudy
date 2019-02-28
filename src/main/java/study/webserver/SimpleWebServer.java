package study.webserver;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.google.common.io.Files;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;


public class SimpleWebServer {
    public static void main(String... args) throws IOException {
        int port = 8000;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        System.out.println("server started at " + port);
        server.createContext("/", new RootHandler());
        server.setExecutor(null);
        server.start();
    }
    
    public static class RootHandler implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException {
            File indexHtml = new File("~");
            he.sendResponseHeaders(200, indexHtml.length());
            
            OutputStream os = he.getResponseBody();
            Files.copy(indexHtml, os);
            os.close();
        }
    }
}
