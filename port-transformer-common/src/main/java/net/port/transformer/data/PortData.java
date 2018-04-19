package net.port.transformer.data;


/**
 * 根据接口转换而成的结构数据
 * Created by zhongyongsheng on 2018/4/13.
 */
public interface PortData<V> {

    void putValue(String key, V value);

    V getValue(String key);
}
