import com.sun.tools.attach.VirtualMachine;
import com.sun.tools.attach.VirtualMachineDescriptor;

import java.util.List;

public class get_PID {
    public static void main(String[] args) {

        //调用VirtualMachine.list()获取正在运行的JVM列表
        List<VirtualMachineDescriptor> list = VirtualMachine.list();
        for(VirtualMachineDescriptor vmd : list){

            //遍历每一个正在运行的JVM，如果JVM名称为get_PID则返回其PID

            System.out.println(vmd.displayName());
        }

    }
}
