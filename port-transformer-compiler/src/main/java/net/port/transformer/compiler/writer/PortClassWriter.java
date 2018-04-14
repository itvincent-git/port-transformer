package net.port.transformer.compiler.writer;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * Created by zhongyongsheng on 2018/4/14.
 */
public abstract class PortClassWriter {
    ClassName className;

    public PortClassWriter(ClassName className) {
        this.className = className;
    }

    public void write(ProcessingEnvironment processingEnvironment) throws IOException {
        TypeSpec.Builder typeSpecBuilder = createTypeSpecBuilder();
        JavaFile.builder(className.packageName(),
                typeSpecBuilder.build())
                .build()
                .writeTo(processingEnvironment.getFiler());
    }

    protected abstract TypeSpec.Builder createTypeSpecBuilder();
}
