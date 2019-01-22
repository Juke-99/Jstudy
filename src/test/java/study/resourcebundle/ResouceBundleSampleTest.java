package study.resourcebundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

import java.util.Locale;

import org.junit.Test;

public class ResouceBundleSampleTest {

	@Test
	public void testGetResource() {
		//Locale locale = mock(Locale.class);
		ResouceBundleSample sample = new ResouceBundleSample();
		
		//assertThat(sample.getResource(locale), is("hello"));
		assertThat(sample.getResource(Locale.JAPANESE), is("ÉnÉçÅ["));
		assertThat(sample.getResource(Locale.ENGLISH), is("hello"));
	}

}
