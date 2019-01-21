package study.arrays;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class ArraysSampleTest {
	public String[] array = {"one", "two", "three"};
	public String[] arrayIncludedNull = {"one", "two", "three", null};
	public String[] arrayBiggestInThisClass = {"A", null, "T", "M", "", "O", "P", "C", "P", "H"};

	@Test
	public void testStringArraysCopyOf() {
		String[] copyedArray = Arrays.copyOf(array, 3);
		
		assertEquals(new String[] {"one", "two", "three"}, copyedArray);
	}
	
	@Test
	public void testStringOverArraysCopyOf() {
		String[] copyedArray = Arrays.copyOf(array, 6);
		
		assertEquals(new String[] {"one", "two", "three", null, null, null}, copyedArray);
	}
	
	@Test
	public void testStringArraysCopyOfNull() {
		String[] copyedArray = Arrays.copyOf(arrayIncludedNull, 4);
		
		assertEquals(new String[] {"one", "two", "three", null}, copyedArray);
	}
	
	@Test
	public void testStringCopyOfRange() {
		String[] copyedArray = Arrays.copyOfRange(arrayBiggestInThisClass, 3, 6);
		
		assertEquals(new String[] {"M", "", "O"}, copyedArray);
	}
	
	@Test
	public void testStringOverCopyOfRange() {
		String[] copyedArray = Arrays.copyOfRange(arrayBiggestInThisClass, 8, 12);
		
		assertEquals(new String[] {"P", "H", null, null}, copyedArray);
	}
}
