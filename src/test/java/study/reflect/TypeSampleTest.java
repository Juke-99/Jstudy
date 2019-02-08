package study.reflect;

import static org.junit.Assert.*;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.google.common.reflect.TypeToken;

public class TypeSampleTest extends ArrayList<String> {
	private static final long serialVersionUID = 1L;
	TypeSample type = new TypeSample();

	@Test
	public void testType() {
		System.out.println(((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
		System.out.println(new TypeToken<List<Integer>>() {
			private static final long serialVersionUID = 3470170160783941124L;
		}.getType());
		
		System.out.println(new TypeToken<Type>() {
			private static final long serialVersionUID = 3470170160783941124L;
		}.getType());
	}

}
