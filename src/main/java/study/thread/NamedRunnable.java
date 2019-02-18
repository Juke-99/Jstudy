package study.thread;

import java.util.Locale;
import java.util.logging.Logger;

public abstract class NamedRunnable implements Runnable {
	private static final Logger logger = Logger.getLogger(NamedRunnable.class.getName());
	final String name;

	public NamedRunnable(String name, Object... args) {
		this.name = String.format(Locale.US, name, args);
	}
	
	@Override
	public void run() {
		logger.info("run server");
		String previousThread = Thread.currentThread().getName();
		Thread.currentThread().setName(name);
		
		try {
			execute();
		} finally {
			Thread.currentThread().setName(previousThread);
		}
	}
	
	protected abstract void execute();
}
