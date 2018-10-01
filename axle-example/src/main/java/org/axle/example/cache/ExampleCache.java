package org.axle.example.cache;

import java.util.HashMap;
import java.util.Map;

import org.axle.example.model.Example;

public class ExampleCache {
	static Map<Integer,Example> cache = new HashMap<>();
	static {
		Example example1 = new Example();
		example1.setId(1);
		example1.setUsername("Jack Ma");
		example1.setNickname("Jack");
		example1.setAge(40);
		cache.put(example1.getId(), example1);
		Example example2 = new Example();
		example2.setId(2);
		example2.setUsername("Alex Zhou");
		example2.setNickname("Alex");
		example2.setAge(35);
		cache.put(example2.getId(), example2);
		Example example3 = new Example();
		example3.setId(3);
		example3.setUsername("Alice Cai");
		example3.setNickname("Alice");
		example3.setAge(28);
		cache.put(example3.getId(), example3);
	}
	
	public Example getById(int id) {
		if(!cache.containsKey(id)) throw new RuntimeException("unknown example entity");
		return cache.get(id);
	}
	
	public int generateId() {
		return cache.size()+1;
	}
	
	public Example insert(Example example) {
		if(cache.containsKey(example.getId())) throw new RuntimeException("example has exsiting");
		cache.put(example.getId(), example);
		return example;
	}
}
