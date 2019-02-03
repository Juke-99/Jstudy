package study.atomic;

import static org.junit.Assert.assertEquals;

import java.util.logging.Logger;

import org.junit.Test;

public class AtomicSampleTest {
	Logger logger = Logger.getLogger("AtomicSampleTest");
	AtomicSample atomics = new AtomicSample();
	
	@Test
	public void testAtomicInteger() {
		logger.info("atomic start");
		assertEquals(100, atomics.atomicInteger());
		
		logger.info("non atomic start");
		assertEquals(100, atomics.nonAtomicInteger());
	}
}
