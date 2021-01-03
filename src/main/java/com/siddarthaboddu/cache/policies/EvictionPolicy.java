package com.siddarthaboddu.cache.policies;

public interface EvictionPolicy<Key> {
	public void keyAccessed(Key key);
	public Key evict();
}
