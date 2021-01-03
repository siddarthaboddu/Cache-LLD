package com.siddarthaboddu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.siddarthaboddu.cache.Cache;
import com.siddarthaboddu.cache.factories.CacheFactory;

public class CacheTest {

	Cache<Integer, String> cache;
	
	@BeforeEach
	public void init() {
		this.cache = new CacheFactory<Integer, String>().defaultSingleLevelCache(2);
	}
	
	@Test
	public void test1() {
		cache.put(1,  "a");
		cache.put(2, "b");
		
		assertEquals(cache.get(1), "a");
		assertEquals(cache.get(2), "b");
	}

	@Test
	public void test2() {
		cache.put(1,  "a");
		cache.put(2, "b");
		cache.put(3, "c");
		
		assertNull(cache.get(1));
		assertEquals(cache.get(2), "b");
		assertEquals(cache.get(3), "c");
	}
	
	@Test
	public void test3() {
		cache.put(1,  "a");
		cache.put(2, "b");
		cache.put(1,  "a");
		cache.put(3, "c");
		
		assertEquals(cache.get(1), "a");
		assertNull(cache.get(2));
		assertEquals(cache.get(3), "c");
	}
}
