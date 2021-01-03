package com.siddarthaboddu;

import com.siddarthaboddu.cache.Cache;
import com.siddarthaboddu.cache.factories.CacheFactory;

public class CacheLLDApplication {

	public static void main(String[] args) {
		Cache<Integer, String> cache = new CacheFactory<Integer, String>().defaultSingleLevelCache(10);
		
		cache.put(1, "first key");
		System.out.println(cache.get(1));
	}
}
