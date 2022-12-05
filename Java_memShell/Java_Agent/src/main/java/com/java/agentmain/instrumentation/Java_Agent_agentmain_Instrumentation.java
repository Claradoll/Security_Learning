package com.java.agentmain.instrumentation;

import java.lang.instrument.Instrumentation;

public class Java_Agent_agentmain_Instrumentation {
    public static void agentmain(String args, Instrumentation inst) throws InterruptedException {
        Class [] classes = inst.getAllLoadedClasses();

        for(Class cls : classes){
            System.out.println("------------------------------------------");
            System.out.println("class: "+cls.getName());
            System.out.println("modify: "+inst.isModifiableClass(cls));
        }
    }
}

