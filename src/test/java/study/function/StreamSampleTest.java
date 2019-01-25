package study.function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.logging.Logger;

import org.junit.Test;

public class StreamSampleTest {
	String[] result = {"one", "two", "three", "1", "2", "3", "o", "t", "h"};
	Logger logger = Logger.getLogger("StreamSampleTest");

	@Test
	public void testGetStreamingList() {
		StreamSample ss = new StreamSample();
		
		ss.getStreamingList().forEach(item -> {
			logger.info(item);
		});
	}
}
