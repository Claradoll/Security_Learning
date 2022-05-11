package com.java.agentmain.instrumentation.transformer;

import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class Java_Agent_agentmain_transform {
    public static void agentmain(String args, Instrumentation inst) throws InterruptedException, UnmodifiableClassException {
        Class [] classes = inst.getAllLoadedClasses();

        //获取目标JVM加载的全部类
        for(Class cls : classes){
            if (cls.getName().equals("com.sleep.hello.Sleep_Hello")){

                //添加一个transformer到Instrumentation，并重新触发目标类加载
                inst.addTransformer(new Hello_Transform(),true);
                inst.retransformClasses(cls);
            }
        }
    }
}
