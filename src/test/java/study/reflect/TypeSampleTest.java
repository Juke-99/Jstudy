package study.reflect;

import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.IntegerSequence;
import org.junit.Test;

import com.google.common.reflect.TypeToken;

import static org.assertj.core.api.Assertions.assertThat;

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
	
	@Test
	public void testGetRowType() {
		Type stringType = new TypeToken<List<String>>() {
			private static final long serialVersionUID = 1L;
		}.getType();
		
		assertThat((Class<?>) (((ParameterizedType) stringType).getRawType())).isSameAs(List.class);
		
		Type arrayType = new TypeToken<String[]>() {
			private static final long serialVersionUID = 1L;
		}.getType();
		
//		Type componentType = ((GenericArrayType) arrayType).getGenericComponentType();
//		assertThat(Array.newInstance((Class<?>) componentType, 0).getClass()).isSameAs(String[].class);
		
		Type wildType = ((ParameterizedType) new TypeToken<List<? extends IntegerSequence>>() {
			private static final long serialVersionUID = 1L;
		}.getType()).getActualTypeArguments()[0];
		
		assertThat(((WildcardType) wildType).getUpperBounds()[0]).isSameAs(IntegerSequence.class);
	}
}
