package net.port.transformer.sample;

import android.util.Log;

import net.port.transformer.data.DefaultPortData;
import net.port.transformer.data.PortData;
import net.port.transformer.processor.PortTransformerProcessor;

/**
 * Created by zhongyongsheng on 2018/4/16.
 */
public class ReportProcessor implements PortTransformerProcessor<Void, DefaultPortData<String>>{
    private static final String TAG = "ReportProcessor";
    @Override
    public Void doProcess(DefaultPortData<String> para) {
        Log.d(TAG, "doProcess " + para);
        for (String key : para.keySet()) {
            Log.d(TAG, "doProcess: key:" + key);
        }
        return null;
    }
}
