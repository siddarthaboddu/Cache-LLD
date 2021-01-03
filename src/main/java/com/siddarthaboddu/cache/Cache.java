package com.siddarthaboddu.cache;

public interface Cache<Key, Value> {
	public Value get(Key key);
	public void put(Key key, Value value);
}
