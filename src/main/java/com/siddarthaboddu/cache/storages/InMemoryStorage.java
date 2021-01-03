package com.siddarthaboddu.cache.storages;

import java.util.HashMap;
import java.util.Map;

import com.siddarthaboddu.cache.exceptions.KeyNotFoundException;
import com.siddarthaboddu.cache.exceptions.StorageFullException;

public class InMemoryStorage<Key, Value> implements Storage<Key, Value>{

	private final Map<Key, Value> map;
	private final int capacity;
	
	public InMemoryStorage(int capacity) {
		this.map = new HashMap<Key, Value>();
		this.capacity = capacity;
	}
	
	@Override
	public void put(Key key, Value value) {
		if(map.size() >= capacity) {
			throw new StorageFullException("storage full");
		}
		map.put(key, value);
	}

	@Override
	public Value get(Key key) {
		if(!map.containsKey(key)) {
			throw new KeyNotFoundException(String.format("Key '%s' not found in storage", key));
		}
		return map.get(key);
	}

	@Override
	public Value remove(Key key) {
		return map.remove(key);
	}

}
