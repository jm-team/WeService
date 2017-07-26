package com.jumore.we.service.utils;

import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodAdapter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Type;

public class ParameterNameUtils {

    /** 
     * 获取指定类指定方法的参数名 
     * 
     * @param clazz 要获取参数名的方法所属的类 
     * @param method 要获取参数名的方法 
     * @return 按参数顺序排列的参数名列表，如果没有参数，则返回null 
     * @throws IOException 
     */  
    public static String[] getMethodParameterNamesByAsm4(Class<?> clazz, final Method method) throws IOException {  
        final String methodName = method.getName();
        final Class<?>[] methodParameterTypes = method.getParameterTypes();
        final int methodParameterCount = methodParameterTypes.length;
        final String className = method.getDeclaringClass().getName();
        final boolean isStatic = Modifier.isStatic(method.getModifiers());
        final String[] methodParametersNames = new String[methodParameterCount];

        ClassReader cr = new ClassReader(className);
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cr.accept(new ClassAdapter(cw) {
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {

                MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

                final Type[] argTypes = Type.getArgumentTypes(desc);

                //参数类型不一致
                if (!methodName.equals(name) || !matchTypes(argTypes, methodParameterTypes)) {
                    return mv;
                }
                return new MethodAdapter(mv) {
                    public void visitLocalVariable(String name, String desc, String signature, Label start, Label end, int index) {
                        //如果是静态方法，第一个参数就是方法参数，非静态方法，则第一个参数是 this ,然后才是方法的参数
                        int methodParameterIndex = isStatic ? index : index - 1;
                        if (0 <= methodParameterIndex && methodParameterIndex < methodParameterCount) {
                            methodParametersNames[methodParameterIndex] = name;
                        }
                        super.visitLocalVariable(name, desc, signature, start, end, index);
                    }
                };
            }
        }, 0);
        return methodParametersNames;
    } 
    
    /**
     * 比较参数是否一致
     */
    private static boolean matchTypes(Type[] types, Class<?>[] parameterTypes) {
        if (types.length != parameterTypes.length) {
            return false;
        }
        for (int i = 0; i < types.length; i++) {
            if (!Type.getType(parameterTypes[i]).equals(types[i])) {
                return false;
            }
        }
        return true;
    }
}
