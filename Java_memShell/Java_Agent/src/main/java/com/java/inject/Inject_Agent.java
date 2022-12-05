package com.java.inject;

import com.sun.tools.attach.*;

import java.io.IOException;
import java.util.List;

public class Inject_Agent {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        //调用VirtualMachine.list()获取正在运行的JVM列表
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for(VirtualMachineDescriptor vmd : list){

            //遍历每一个正在运行的JVM，如果JVM名称为Sleep_Hello则连接该JVM并加载特定Agent
            if(vmd.displayName().equals("com.sleep.hello.Sleep_Hello")){

                //连接指定JVM
                VirtualMachine virtualMachine = VirtualMachine.attach(vmd.id());
                //加载Agent
                virtualMachine.loadAgent("out/artifacts/Java_Agent_jar/Java_Agent.jar");
                //断开JVM连接
                virtualMachine.detach();
            }

        }
    }
}
