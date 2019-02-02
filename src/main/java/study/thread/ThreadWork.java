package study.thread;

import java.util.concurrent.CountDownLatch;

public class ThreadWork extends Thread {
	private CountDownLatch latch;
	
	ThreadWork(CountDownLatch latch) {
		this.latch = latch;
	}
	
	@Override
	public void run() {
		try {
			if(latch.getCount() == 0) {
				System.out.println("finish");
				awaiting();
			} else {
				System.out.println("coutdouwn: " + latch.getCount());
				latch.countDown();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void awaiting() throws InterruptedException {
		latch.await();
	}
}
