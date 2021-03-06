package net.port.transformer.sample;

import android.util.Log;

import net.port.transformer.data.DefaultPortData;
import net.port.transformer.processor.PortTransformerProcessor;

/**
 * 使用Object作为Value类型
 * Created by zhongyongsheng on 2018/4/16.
 */
public class ReportObjectProcessor implements PortTransformerProcessor<Void, DefaultPortData<Object>> {
    private static final String TAG = "ReportObjectProcessor";

    @Override
    public Void doProcess(DefaultPortData<Object> para) {
        for (String key : para.keySet()) {
            Log.d(TAG, "doProcess: key:" + key + ", value:" +para.getValue(key));
        }
        return null;
    }
}
