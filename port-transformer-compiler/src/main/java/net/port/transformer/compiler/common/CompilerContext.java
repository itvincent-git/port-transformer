package net.port.transformer.compiler.common;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class CompilerContext {

    public static CompilerContext defaultIntance;
    public ProcessingEnvironment processingEnvironment;
    public Log log;
    public String keepClass = "android.support.annotation.Keep";

    public CompilerContext(ProcessingEnvironment env) {
        processingEnvironment = env;
        log = new Log(env.getMessager(), null);
        if (processingEnvironment.getOptions() != null) {
            String keepClass = processingEnvironment.getOptions().get("keepClass");
            if (keepClass != null) {
                this.keepClass = keepClass;
            }
        }
    }
}
