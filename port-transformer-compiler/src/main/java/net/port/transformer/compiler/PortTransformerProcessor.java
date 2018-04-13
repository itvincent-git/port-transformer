package net.port.transformer.compiler;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.common.collect.SetMultimap;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortParameter;
import net.port.transformer.annotation.PortProcessor;
import net.port.transformer.annotation.PortTransformer;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import javax.lang.model.element.Element;

/**
 * Created by zhongyongsheng on 2018/4/13.
 */
public class PortTransformerProcessor extends BasicAnnotationProcessor {
    @Override
    protected Iterable<? extends ProcessingStep> initSteps() {
        PortContext portContext = new PortContext(processingEnv);
        return Arrays.asList(new PortProcessingStep(portContext));
    }

    class PortProcessingStep extends PortContextProcessing {

        PortProcessingStep(PortContext context) {
            portContext = context;
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
            return null;
        }
    }

    abstract class PortContextProcessing implements ProcessingStep {
        PortContext portContext;
    }
}
