package com.siddarthaboddu.cache;

import com.siddarthaboddu.cache.exceptions.KeyNotFoundException;
import com.siddarthaboddu.cache.exceptions.StorageFullException;
import com.siddarthaboddu.cache.policies.EvictionPolicy;
import com.siddarthaboddu.cache.storages.Storage;

public class SingleLevelCache<Key, Value> implements Cache<Key, Value>{

	private final EvictionPolicy<Key> evictionPolicy;
	private final Storage<Key, Value> storage;
	
	public SingleLevelCache(EvictionPolicy<Key> evictionPolicy, Storage<Key, Value> storage) {
		this.evictionPolicy = evictionPolicy;
		this.storage = storage;
	}
	
	@Override
	public Value get(Key key) {
		Value value = null;
		try {
			value = storage.get(key);
			evictionPolicy.keyAccessed(key);
		} catch(KeyNotFoundException e) {
			System.out.println(e);
		}
		return value;
	}

	@Override
	public void put(Key key, Value value) {
		try {
			storage.put(key, value);
			evictionPolicy.keyAccessed(key);
		} catch(StorageFullException e) {
			Key evictKey = evictionPolicy.evict();
			if(evictKey == null) {
				throw new RuntimeException("Unexpected state => evictkey null but storage is full");
			}
			storage.remove(evictKey);
			storage.put(key, value);
			evictionPolicy.keyAccessed(key);
		}
	}

}
