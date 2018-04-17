package net.port.transformer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface PortProcessor {
    Class<?> processor();
}
