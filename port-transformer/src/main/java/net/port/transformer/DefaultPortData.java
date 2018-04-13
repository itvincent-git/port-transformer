package net.port.transformer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class DefaultPortData<V> implements PortData {
    private Map<String, V> map = new HashMap<>();

    @Override
    public V getValue(String key) {
        return map.get(key);
    }
}
