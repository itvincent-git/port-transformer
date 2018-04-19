package net.port.transformer.sample;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortPair;
import net.port.transformer.annotation.PortPairArray;
import net.port.transformer.annotation.PortParameter;
import net.port.transformer.annotation.PortProcessor;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
@PortInterface
public interface SampleReport {
    String SAMPLE_EVENT = "SAMPLE_EVENT_ID";

    @PortProcessor(processor = ReportProcessor.class)
    @PortPairArray(pairs = {@PortPair(key = "event_id", value = SAMPLE_EVENT), @PortPair(key = "type", value = "new")})
    void report(@PortParameter("function_id")String funcId,
                @PortParameter("act_uid")String uid);

    @PortProcessor(processor = ReportProcessor.class)
    @PortPair(key = "event_id", value = SAMPLE_EVENT)
    void report2(@PortParameter("function_id")String funcId,
                @PortParameter("act_uid")String uid,
                @PortParameter("time")String time);
}
