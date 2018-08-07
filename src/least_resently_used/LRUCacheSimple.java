package least_resently_used;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCacheSimple {

	private static final int cacheSize = 100;

	public static void main(String[] args) {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>((int) Math.ceil(cacheSize / 0.75f) + 1, 0.75f, true) {
			private static final long serialVersionUID = 1L;

			@Override
			protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
				return size() > cacheSize;
			}
		};
		
		map.put(1, "你好");
		map.put(2, "符文法师");
		System.out.println(map.toString());
	}
}
