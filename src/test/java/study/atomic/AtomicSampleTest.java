package study.atomic;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AtomicSampleTest {
	AtomicSample atomics = new AtomicSample();
	
	@Test
	public void testAtomicInteger() {
		assertEquals(100, atomics.atomicInteger());
	}
}
