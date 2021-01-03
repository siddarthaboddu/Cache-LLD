package com.siddarthaboddu.cache.factories;

import com.siddarthaboddu.cache.Cache;
import com.siddarthaboddu.cache.SingleLevelCache;
import com.siddarthaboddu.cache.policies.EvictionPolicy;
import com.siddarthaboddu.cache.policies.LRUEvictionPolicy;
import com.siddarthaboddu.cache.storages.InMemoryStorage;
import com.siddarthaboddu.cache.storages.Storage;

public class CacheFactory<Key, Value> {

	public Cache<Key, Value> defaultSingleLevelCache(int capacity) {
		EvictionPolicy<Key> evictionPolicy = new LRUEvictionPolicy<>();
		Storage<Key, Value> storage = new InMemoryStorage<>(capacity);
		return new SingleLevelCache<Key, Value>(evictionPolicy, storage);
	}
}
