package net.port.transformer.data;

import java.util.HashMap;

/**
 * 根据接口转换而成的map结构数据
 * Created by zhongyongsheng on 2018/4/13.
 */
public class DefaultPortData<V> extends HashMap<String, V> implements PortData<V> {

    @Override
    public void putValue(String key, V value) {
        put(key, value);
    }

    @Override
    public V getValue(String key) {
        return get(key);
    }

}
