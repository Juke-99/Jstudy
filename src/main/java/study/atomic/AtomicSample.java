package study.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicSample {
	public int atomicInteger() {
		AtomicInteger atInt = new AtomicInteger();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		IntStream.range(0, 100).forEach(i -> executor.submit(atInt::incrementAndGet));
		
		stop(executor);
		
		return atInt.get();
	}
	
	public void stop(ExecutorService executor) {
		try {
			executor.shutdown();
			executor.awaitTermination(30, TimeUnit.SECONDS);
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		} finally {
			executor.shutdownNow();
		}
	}
}
