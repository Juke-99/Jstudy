package study.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamSample {
	List<String> list1;
	List<String> list2;
	List<String> list3;
	Logger logger = Logger.getLogger("StreamSample");
	
	public List<String> getStreamingList() {
		list1 = Arrays.asList("one", "two", "three");
		list2 = Arrays.asList("1", "2", "3");
		list3 = Arrays.asList("o", "t", "h");
		
		return Stream.of(list1.stream(), list2.stream(), list3.stream())
				.flatMap(Function.identity())
				.collect(toList());
	}
	
	public void displayValues() {
		list1 = Arrays.asList("one", "two", "three");
		
		logger.info("flatMap");
		Stream.of(list1.stream(), list1.stream(), list1.stream())
			.flatMap(t -> t).forEach(t -> System.out.println(t));  // "one", "two", "three", "one", "two", "three", "one", "two", "three"
		
		logger.info("non flatMap");
		Stream.of(list1.stream(), list1.stream(), list1.stream())
			.forEach(t -> System.out.println(t));
	}
}