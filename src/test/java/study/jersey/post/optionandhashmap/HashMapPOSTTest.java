package study.jersey.post.optionandhashmap;

import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;

import org.junit.Test;

public class HashMapPOSTTest {
	@Test
	public void testPostValue() {
		Form form1 = new Form();
		form1.param("value", "1");
		
		Form form2 = new Form();
		form2.param("value", null);
		
		Form form3 = new Form();
		form3.param("value", "");
		
		//Response res = target("/hashmap")
	}
}
