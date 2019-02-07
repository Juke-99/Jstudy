package study.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TypeSample {
	List<String> stringList = new ArrayList<>();
	List<Error> errorList = new ArrayList<>();
	
	public Type getActualType() {
		//ParameterizedType types = stringList.getClass()
		return null;
	}
}
