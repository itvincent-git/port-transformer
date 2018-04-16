package net.port.transformer.processor;

import com.google.auto.common.BasicAnnotationProcessor;
import com.google.auto.service.AutoService;
import com.google.common.collect.SetMultimap;

import net.port.transformer.annotation.PortInterface;
import net.port.transformer.annotation.PortParameter;
import net.port.transformer.annotation.PortProcessor;
import net.port.transformer.annotation.PortTransformer;
import net.port.transformer.util.Util;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortTransformerData;
import net.port.transformer.compiler.writer.PortTransformerWriter;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

import javax.annotation.processing.Processor;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;

/**
 * Processor 入口类
 * Created by zhongyongsheng on 2018/4/13.
 */
@AutoService(Processor.class)
public class PortTransformerProcessor extends BasicAnnotationProcessor {
    CompilerContext portContext;
    @Override
    protected Iterable<? extends ProcessingStep> initSteps() {
        portContext = new CompilerContext(processingEnv);
        return Arrays.asList(new PortProcessingStep(portContext));
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    class PortProcessingStep extends PortContextProcessing {

        PortProcessingStep(CompilerContext context) {
            compilerContext = context;
        }

        @Override
        public Set<? extends Class<? extends Annotation>> annotations() {
            Set<Class<? extends Annotation>> set = new HashSet<>();
            set.add(PortInterface.class);
            set.add(PortParameter.class);
            set.add(PortProcessor.class);
            set.add(PortTransformer.class);
            return set;
        }

        @Override
        public Set<? extends Element> process(SetMultimap<Class<? extends Annotation>, Element> elementsByAnnotation) {
            Set<Element> portTransformerSet = elementsByAnnotation.get(PortTransformer.class);
            Stream<PortTransformerData> portTransformerDataStream = portTransformerSet.stream().map(new Function<Element, PortTransformerData>() {
                @Override
                public PortTransformerData apply(Element element) {
                    return new PortTransformerAnnotationProcessor(compilerContext, Util.toTypeElement(element)).process();
                }
            });

            portTransformerDataStream.forEach(new Consumer<PortTransformerData>() {
                @Override
                public void accept(PortTransformerData portTransformerData) {
                    try {
                        new PortTransformerWriter(portTransformerData).write(processingEnv);
                    } catch (IOException e) {
                        portContext.log.error("PortTransformerWriter error", e.getMessage());
                    }
                }
            });

            return new HashSet<>();
        }
    }

    abstract class PortContextProcessing implements ProcessingStep {
        CompilerContext compilerContext;
    }
}
