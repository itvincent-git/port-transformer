package net.port.transformer.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解在接口方法上，标明将多个@PortPair的key,value传入处理器
 * Created by zhongyongsheng on 2018/4/13.
 */
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.METHOD)
public @interface PortPairArray {
    PortPair[] pairs();
}
