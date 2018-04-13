package net.port.transformer.compiler;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class PortContext {

    public ProcessingEnvironment processingEnvironment;

    public PortContext(ProcessingEnvironment env) {
        processingEnvironment = env;
    }
}
