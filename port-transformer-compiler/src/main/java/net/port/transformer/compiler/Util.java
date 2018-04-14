package net.port.transformer.compiler;

import com.google.auto.common.AnnotationMirrors;
import com.google.auto.common.MoreElements;

import java.lang.annotation.Annotation;
import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class Util {

    /**
     * 转化为TypeElement，包含类的名称，实现的接口，父类等
     * @param element
     * @return
     */
    public static TypeElement toTypeElement(Element element) {
        return MoreElements.asType(element);
    }

    /**
     * AnnotationMirror 包含注解的类型，注解元素的值
     * @param element
     * @param annotationClass
     * @return
     */
    public static AnnotationMirror getAnnotationMirror(Element element, Class<? extends Annotation> annotationClass) {
        return MoreElements.getAnnotationMirror(element, annotationClass).orNull();
    }

    /**
     * 根据元素名称返回注解的元素的值
     * @param annotationMirror
     * @param name
     * @return
     */
    public static AnnotationValue getAnnotationValue(AnnotationMirror annotationMirror, String name) {
        return AnnotationMirrors.getAnnotationValue(annotationMirror, name);
    }

    /**
     * 返回所有成员变量及方法
     * @param processingEnvironment
     * @param element
     * @return
     */
    public static List<? extends Element> getAllMembers(ProcessingEnvironment processingEnvironment, TypeElement element) {
        return processingEnvironment.getElementUtils().getAllMembers(element);
    }
}
