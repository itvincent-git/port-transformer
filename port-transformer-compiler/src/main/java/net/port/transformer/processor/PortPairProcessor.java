package net.port.transformer.processor;

import net.port.transformer.annotation.PortPair;
import net.port.transformer.annotation.PortPairArray;
import net.port.transformer.compiler.common.CompilerContext;
import net.port.transformer.compiler.data.PortPairData;
import net.port.transformer.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public class PortPairProcessor {

    CompilerContext compileContext;
    ExecutableElement element;

    PortPairProcessor(CompilerContext context, ExecutableElement element) {
        compileContext = context;
        this.element = element;
    }

    PortPairData process() {
        AnnotationMirror portPairArrayMirror = Util.getAnnotationMirror(element, PortPairArray.class);
        AnnotationMirror portPairMirror = Util.getAnnotationMirror(element, PortPair.class);
        if (portPairArrayMirror != null) {
            AnnotationValue processorValue = Util.getAnnotationValue(portPairArrayMirror, "pairs");
            List<AnnotationMirror> pairs = (List<AnnotationMirror>) processorValue.getValue();
            List<PortPairData.PairData> pairDataList = pairs.stream()
                    .map(portPair ->
                            new PortPairData.PairData(Util.getAnnotationValue(portPair, "key").toString(),
                            Util.getAnnotationValue(portPair, "value").toString()))
                    .collect(Collectors.toList());
            return new PortPairData(pairDataList);
        } else if (portPairMirror != null) {
            AnnotationValue key = Util.getAnnotationValue(portPairMirror, "key");
            AnnotationValue value = Util.getAnnotationValue(portPairMirror, "value");
            List<PortPairData.PairData> pairDataList = new ArrayList<>();
            pairDataList.add(new PortPairData.PairData(key.toString(), value.toString()));
            return new PortPairData(pairDataList);
        } else {
            return new PortPairData(new ArrayList<>());
        }
    }
}
