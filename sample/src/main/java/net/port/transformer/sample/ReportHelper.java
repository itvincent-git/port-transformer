package net.port.transformer.sample;

import android.content.Context;

import net.port.transformer.PortTransformerBuilder;
import net.port.transformer.annotation.PortTransformer;

import java.io.Serializable;

/**
 * 此类需要排除混淆，加上Serializable可排除混淆
 * Created by zhongyongsheng on 2018/4/13.
 */
@PortTransformer
public abstract class ReportHelper implements Serializable {
    private static volatile ReportHelper sInstance;

    public static ReportHelper getInstance(Context context) {
        if (sInstance != null) {
            return sInstance;
        } else {
            synchronized (ReportHelper.class) {
                if (sInstance == null)
                    sInstance = PortTransformerBuilder.newBuilder(context, ReportHelper.class).build();
                return sInstance;
            }
        }
    }

    abstract SampleReport getSampleReport();
}
