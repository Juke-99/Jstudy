package study.atomic;

import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class AtomicSample {
	HashMap<Integer, Integer> a = new HashMap<>();
	
	public int atomicInteger() {
		AtomicInteger atInt = new AtomicInteger();
		ExecutorService executor = Executors.newFixedThreadPool(2);
		IntStream.range(0, 100).forEach(i -> executor.submit(atInt::incrementAndGet));
		
		stop(executor);
		
		return atInt.get();
	}
	
	// I don't know that different between Integer and AtomicInteger.
	@SuppressWarnings("deprecation")
	public int nonAtomicInteger() {
		Integer integer = new Integer(100);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		IntStream.range(0, 100).forEach(i -> executor.submit(integer::intValue));
		
		stop(executor);
		
		return integer;
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
