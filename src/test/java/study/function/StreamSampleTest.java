package study.function;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import org.junit.Test;

public class StreamSampleTest {
	List<String> result;
	StreamSample ss = new StreamSample();
	
	Logger logger = Logger.getLogger("StreamSampleTest");

	@Test
	public void testGetStreamingList() {
		result = Arrays.asList("one", "two", "three", "1", "2", "3", "o", "t", "h");
		
		assertThat(ss.getStreamingList(), is(result));
	}
	
	@Test
	public void testDisplayValues() {
		ss.displayValues();
	}
}
