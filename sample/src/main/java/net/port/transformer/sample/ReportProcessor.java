package net.port.transformer.sample;

import android.util.Log;

import net.port.transformer.data.PortData;
import net.port.transformer.processor.PortTransformerProcessor;

/**
 * Created by zhongyongsheng on 2018/4/16.
 */
public class ReportProcessor implements PortTransformerProcessor<Void, PortData<String>>{
    @Override
    public Void doProcess(PortData<String> para) {
        Log.d("ReportProcessor", "doProcess " + para);
        return null;
    }
}
