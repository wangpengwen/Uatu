package com.vinctor.handler;

import com.android.build.api.transform.TransformInvocation;
import com.vinctor.classtree.ClassGraphVisitor;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.Opcodes;

public class PreStatisticsHanller extends BaseHanlder {
    public PreStatisticsHanller(TransformInvocation transformInvocation) {
        super(transformInvocation);
    }

    @Override
    byte[] onHanlerFileInput(byte[] bytes) {
        statistics(bytes);
        return bytes;
    }

    private void statistics(byte[] bytes) {
        ClassReader cr = new ClassReader(bytes);
        ClassGraphVisitor cv = new ClassGraphVisitor(Opcodes.ASM5);
        cr.accept(cv, ClassReader.SKIP_CODE | ClassReader.SKIP_DEBUG | ClassReader.SKIP_FRAMES);
    }

    @Override
    byte[] onHanlerJarInput(byte[] bytes) {
        statistics(bytes);
        return bytes;
    }
}
