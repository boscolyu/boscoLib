package org.bosco.lib.cache;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

	private static final float LoadFactor = 1f;
	int maxCacheSize = 0;
	
	LinkedHashMap<Object, Object> lrucache = null;
	
	public LRUCache(int maxCacheSize) {
		this.maxCacheSize = maxCacheSize;	
		lrucache = new LinkedHashMap<Object, Object>(maxCacheSize, LoadFactor, true) {
			private static final long serialVersionUID = 1L;

			protected boolean removeEldestEntry(Map.Entry<Object,Object> eldest){
				return size() > LRUCache.this.maxCacheSize;
			}
		};
	}
	
	public Object get(Object key) {
		return lrucache.get(key);
	}
	
	public void put(Object key, Object value) {
		lrucache.put(key, value);			
	}
	
	public Object remove(Object key) {
		return lrucache.remove(key);		
	}

	public int maxCacheSize() {
		return maxCacheSize;
	}
	
	public int getCurrentCachesize() {
		return lrucache.size();
	}

}