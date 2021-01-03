package com.siddarthaboddu.cache.storages;

public interface Storage<Key, Value> {
	public void put(Key key, Value value);
	public Value get(Key key);
	public Value remove(Key key);
}
