package least_resently_used;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;

    private final int MAX_CACHE_SIZE;

    public LRUCache(int cacheSize) {
        super((int) (Math.ceil(cacheSize / 0.75) + 1), 0.75f, true);
        MAX_CACHE_SIZE = cacheSize;
    }

    //是否删除最老（末尾）的元素，默认返回false，即不删除老数据
    //这里当 size 大于设定的阈值时执行方法删除最老的元素
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
