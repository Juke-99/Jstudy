package study.function;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class StreamSample {
	List<String> list1 = Arrays.asList("one", "two", "three");
	List<String> list2 = Arrays.asList("1", "2", "3");
	List<String> list3 = Arrays.asList("o", "t", "h");
	
	public List<String> getStreamingList() {
		return Stream.of(list1.stream(), list2.stream(), list3.stream())
			.flatMap(Function.identity())
			.collect(toList());
	}
}
