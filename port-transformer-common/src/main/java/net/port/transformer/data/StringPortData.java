package net.port.transformer.data;

import java.util.HashMap;

/**
 * 根据接口转换而成的map结构数据，Value是String类型。
 * 如果接口上定义的参数类型是非String类型，在转换的时候也会强制转换成String类型，设置进map结构中
 * Created by zhongyongsheng on 2018/4/13.
 */
public class StringPortData extends HashMap<String, String> implements PortData<String> {

    @Override
    public void putValue(String key, String value) {
        put(key, value);
    }

    @Override
    public String getValue(String key) {
        return get(key);
    }

}
