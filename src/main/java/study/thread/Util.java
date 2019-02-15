package study.thread;

import java.util.concurrent.ThreadFactory;

public class Util {
	public static ThreadFactory threadFactory(String name, boolean deamonSwitch) {
		return runnable -> {
			Thread thread = new Thread(runnable, name);
			thread.setDaemon(deamonSwitch);
			return thread;
		};
	}
}
