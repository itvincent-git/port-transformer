package net.port.transformer.sample;

import android.content.Context;

import net.port.transformer.PortTransformerBuilder;
import net.port.transformer.annotation.PortTransformer;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
@PortTransformer
public abstract class ReportHelper {
    private static volatile ReportHelper sInstance;

    public static ReportHelper getInstance(Context context) {
        return PortTransformerBuilder.newBuilder(context, ReportHelper.class).build();
    }

    abstract SampleReport getSampleReport();
}
