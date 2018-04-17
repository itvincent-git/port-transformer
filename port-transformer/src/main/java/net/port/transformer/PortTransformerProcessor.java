package net.port.transformer;

import net.port.transformer.data.PortData;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public interface PortTransformerProcessor<R, P extends PortData> {

    R doProcess(P para);
}
