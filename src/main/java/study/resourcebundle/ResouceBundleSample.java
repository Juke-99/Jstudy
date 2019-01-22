package study.resourcebundle;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResouceBundleSample {
	private ResourceBundle resource;
	
	public String getResource(Locale lang) {
		resource = ResourceBundle.getBundle("study.resourcebundle.config", lang);
		return resource.getString("hello");
	}
}
