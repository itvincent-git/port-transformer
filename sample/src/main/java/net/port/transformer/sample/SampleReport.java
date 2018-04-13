package net.port.transformer.sample;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortParameter;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
@PortInterface
public interface SampleReport {
    void report(@PortParameter("eventId") String eventId,
                @PortParameter("function_id")String funcId,
                @PortParameter("act_uid")String uid);
}
