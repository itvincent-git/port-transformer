package net.port.transformer.compiler.writer;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

import net.port.transformer.compiler.common.CompilerContext;

import java.io.IOException;

/**
 * 写java基类
 * Created by zhongyongsheng on 2018/4/14.
 */
public abstract class PortClassWriter {
    ClassName className;
    CompilerContext compilerContext;

    public PortClassWriter(ClassName className) {
        this.className = className;
    }

    public void write(CompilerContext compilerContext) throws IOException {
        this.compilerContext = compilerContext;
        TypeSpec.Builder typeSpecBuilder = createTypeSpecBuilder();
        JavaFile.builder(className.packageName(),
                typeSpecBuilder.build())
                .build()
                .writeTo(compilerContext.processingEnvironment.getFiler());
    }

    protected abstract TypeSpec.Builder createTypeSpecBuilder();
}
