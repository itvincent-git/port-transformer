package net.port.transformer;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public interface PortData<V> {

    V getValue(String key);
}
