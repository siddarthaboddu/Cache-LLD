package com.siddarthaboddu.cache.storages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.siddarthaboddu.cache.exceptions.KeyNotFoundException;
import com.siddarthaboddu.cache.exceptions.StorageFullException;

public class InMemoryStorageTest {

	InMemoryStorage<String, String> storage;
	
	@BeforeEach
	public void init() {
		storage = new InMemoryStorage<>(3);
	}
	
	@Test
	public void test1() {
		storage.put("1", "a");
		storage.put("2", "b");
		storage.put("3", "c");
		
		assertEquals(storage.get("1"), "a");
		assertEquals(storage.get("2"), "b");
		assertEquals(storage.get("3"), "c");
	}
	
	@Test
	public void test2() {
		assertThrows(KeyNotFoundException.class, ()->storage.get("1"));
	}
	
	@Test
	public void test3() {
		storage.put("1", "a");
		storage.put("2", "b");
		storage.put("3", "c");
		
		assertThrows(StorageFullException.class, ()-> storage.put("4", "d"));

		assertEquals(storage.get("1"), "a");
		assertEquals(storage.get("2"), "b");
		assertEquals(storage.get("3"), "c");
		
		assertEquals(storage.remove("3"), "c");
		
		assertThrows(KeyNotFoundException.class, ()-> storage.get("3"));
		
	}
}
