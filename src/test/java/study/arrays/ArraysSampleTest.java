package study.arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;

import org.junit.Test;

public class ArraysSampleTest {
	public String[] array = {"one", "two", "three"};
	public String[] arrayIncludedNull = {"one", "two", "three", null};
	public String[] arrayBiggestInThisClass = {"A", null, "T", "M", "", "O", "P", "C", "P", "H"};

	@Test
	public void testStringArraysCopyOf() {
		String[] copyedArray = Arrays.copyOf(array, 3);
		
		assertThat(copyedArray, is(new String[] {"one", "two", "three"}));
	}
	
	@Test
	public void testStringOverArraysCopyOf() {
		String[] copyedArray = Arrays.copyOf(array, 6);
		
		assertThat(copyedArray, is(new String[] {"one", "two", "three", null, null, null}));
	}
	
	@Test
	public void testStringArraysCopyOfNull() {
		String[] copyedArray = Arrays.copyOf(arrayIncludedNull, 4);
		
		assertThat(copyedArray, is(new String[] {"one", "two", "three", null}));
	}
	
	@Test
	public void testStringCopyOfRange() {
		String[] copyedArray = Arrays.copyOfRange(arrayBiggestInThisClass, 3, 6);
		
		assertThat(copyedArray, is(new String[] {"M", "", "O"}));
	}
	
	@Test
	public void testStringOverCopyOfRange() {
		String[] copyedArray = Arrays.copyOfRange(arrayBiggestInThisClass, 8, 12);
		
		assertThat(copyedArray, is(new String[] {"P", "H", null, null}));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testStringCopyOfOppositeRange() {
		String[] copyedArray = Arrays.copyOfRange(arrayBiggestInThisClass, 10, 4);
		
		assertThat(copyedArray, is(new String[] {"P", "H", null, null}));
		
//		JUnit5
//		assertThrows(IllegalArgumentException.class, () -> {
//			String[] copyedArray = Arrays.copyOfRange(arrayBiggestInThisClass, 10, 4);
//		});
	}
	
	@Test
	public void testStringFill() {
		String[] fillArray = new String[10];
		Arrays.fill(fillArray, "A");
		
		assertThat(fillArray, is(new String[] {"A", "A", "A", "A", "A", "A", "A", "A", "A", "A"}));
	}
	
	@Test
	public void testStringAlreadyEnterFill() {
		String[] fillArray = new String[10];
		fillArray[0] = "B";
		fillArray[5] = "C";
		
		Arrays.fill(fillArray, "A");
		
		assertThat(fillArray, is(new String[] {"A", "A", "A", "A", "A", "A", "A", "A", "A", "A"}));
	}
}
