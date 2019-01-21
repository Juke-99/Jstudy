package study.arrays;

import java.util.Arrays;

public class ArraysSample {
	public String[] stringCopyArrays(String[] array) {
		return Arrays.copyOf(array, 4);
	}
	
	public String[] stringCopyRangeArrays(String[] array) {
		return Arrays.copyOfRange(array, 4, array.length);
	}
}
