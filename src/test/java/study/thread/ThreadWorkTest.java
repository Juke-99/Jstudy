package study.thread;

import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class ThreadWorkTest {
	@Test
	public void testRun() {
		CountDownLatch latch = new CountDownLatch(3);
		new ThreadWork(latch).start();
	}
}
