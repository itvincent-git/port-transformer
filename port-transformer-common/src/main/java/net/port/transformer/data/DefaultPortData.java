package net.port.transformer.data;

import net.port.transformer.data.PortData;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class DefaultPortData<V> implements PortData<V> {
    private Map<String, V> map = new HashMap<>();

    @Override
    public void putValue(String key, V value) {
        map.put(key, value);
    }

    @Override
    public V getValue(String key) {
        return map.get(key);
    }

    @Override
    public String toString() {
        return "DefaultPortData{" +
                "map=" + map +
                '}';
    }
}
