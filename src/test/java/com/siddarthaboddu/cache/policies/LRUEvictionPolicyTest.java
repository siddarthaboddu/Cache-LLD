package com.siddarthaboddu.cache.policies;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LRUEvictionPolicyTest {

	LRUEvictionPolicy<Integer> evictionPolicy;
	
	@BeforeEach
	public void init() {
		this.evictionPolicy = new LRUEvictionPolicy<>();
	}
	
	@Test
	public void test1() {
		evictionPolicy.keyAccessed(1);
		evictionPolicy.keyAccessed(2);
		evictionPolicy.keyAccessed(3);
		evictionPolicy.keyAccessed(4);
		
		assertEquals(evictionPolicy.evict(), 1);
		assertEquals(evictionPolicy.evict(), 2);
		assertEquals(evictionPolicy.evict(), 3);
		assertEquals(evictionPolicy.evict(), 4);
		assertNull(evictionPolicy.evict());
	}
	
	@Test
	public void test2() {
		evictionPolicy.keyAccessed(1);
		evictionPolicy.keyAccessed(2);
		evictionPolicy.keyAccessed(3);
		evictionPolicy.keyAccessed(1);
		evictionPolicy.keyAccessed(4);
		
		assertEquals(evictionPolicy.evict(), 2);
		assertEquals(evictionPolicy.evict(), 3);
		assertEquals(evictionPolicy.evict(), 1);
		assertEquals(evictionPolicy.evict(), 4);
		assertNull(evictionPolicy.evict());
	}
}
