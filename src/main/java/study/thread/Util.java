package study.thread;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadFactory;

public class Util {
	public static ThreadFactory threadFactory(String name, boolean deamonSwitch) {
		return runnable -> {
			Thread thread = new Thread(runnable, name);
			thread.setDaemon(deamonSwitch);
			return thread;
		};
	}
	
	public static void closeQuietly(ServerSocket serverSocket) {
	    if (serverSocket != null) {
	        try {
	            serverSocket.close();
	        } catch (RuntimeException rethrown) {
	            throw rethrown;
	        } catch (Exception ignored) {
	        }
	    }
	}
	
	public static void closeQuietly(Socket socket) {
	    if (socket != null) {
	        try {
	            socket.close();
	        } catch (RuntimeException rethrown) {
	            throw rethrown;
	        } catch (Exception ignored) {
          }
        }
    }
}
