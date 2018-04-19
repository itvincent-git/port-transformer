package net.port.transformer.processor;

import net.port.transformer.data.PortData;

/**
 * 转换后处理器接口
 * Created by zhongyongsheng on 2018/4/13.
 */
public interface PortTransformerProcessor<R, P extends PortData> {

    /**
     * 处理数据
     * @param para 根据接口转换而成的结构数据
     * @return 暂无用，留后的的扩展
     */
    R doProcess(P para);
}
