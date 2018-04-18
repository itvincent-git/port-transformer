package net.port.transformer.sample;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortParameter;
import net.port.transformer.annotation.PortProcessor;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
@PortInterface
public interface SampleReport {

    @PortProcessor(processor = ReportProcessor.class)
    void report(@PortParameter("event_id") String eventId,
                @PortParameter("function_id")String funcId,
                @PortParameter("act_uid")String uid);

    @PortProcessor(processor = ReportProcessor.class)
    void report2(@PortParameter("event_id") String eventId,
                @PortParameter("function_id")String funcId,
                @PortParameter("act_uid")String uid,
                @PortParameter("time")String time);
}
