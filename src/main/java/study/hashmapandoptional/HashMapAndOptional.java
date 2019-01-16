package study.hashmapandoptional;

import java.util.HashMap;
import java.util.Optional;

public class HashMapAndOptional {
	
	public String hashMapList(HashMap<String, String> map) {
		for(String key : map.keySet()) {
			Optional<String> keyOpt = Optional.ofNullable(key);
			
			if(keyOpt.isPresent()) {
				return "cannot hashMap";
			}
		}
		return null;
	}
	
	//Map<String, String> map = new Map<>();
	
}
