package net.port.transformer.compiler;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.SetMultimap;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortParameter;
import net.port.transformer.annotation.PortProcessor;
import net.port.transformer.annotation.PortTransformer;
import net.port.transformer.compiler.common.CompilerContext;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class PortTransformerProcessor extends BasicAnnotationProcessor {
    @Override
    protected Iterable<? extends ProcessingStep> initSteps() {
        CompilerContext portContext = new CompilerContext(processingEnv);
        return Arrays.asList(new PortProcessingStep(portContext));
    }

    class PortProcessingStep extends PortContextProcessing {

        PortProcessingStep(CompilerContext context) {
            compilerContext = context;
        }

        @Override
        public Set<? extends Class<? extends Annotation>> annotations() {
            Set<Class<? extends Annotation>> set = new TreeSet<>();
            set.add(PortInterface.class);
            set.add(PortParameter.class);
            set.add(PortProcessor.class);
            set.add(PortTransformer.class);
            return set;
        }

        @Override
        public Set<? extends Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
            Set<Element> portTransformerSet = elementsByAnnotation.get(PortTransformer.class);
            for (Element element : portTransformerSet) {
                new PortTransformerAnnotationProcessor(compilerContext, Util.toTypeElement(element)).process();
            }
            return new HashSet<>();
        }
    }

    abstract class PortContextProcessing implements ProcessingStep {
        CompilerContext compilerContext;
    }
}
