package net.port.transformer;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public interface PortData<V> {

    void putValue(String key, V value);

    V getValue(String key);
}
