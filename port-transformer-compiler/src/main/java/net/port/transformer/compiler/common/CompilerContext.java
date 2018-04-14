package net.port.transformer.compiler.common;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class CompilerContext {

    public ProcessingEnvironment processingEnvironment;
    public Log log;

    public CompilerContext(ProcessingEnvironment env) {
        processingEnvironment = env;
        log = new Log(env.getMessager(), null);
    }
}
