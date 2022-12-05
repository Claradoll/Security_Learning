package Tools;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Hessian_Tools {

    public static byte[] Hessian_Serial(Object o) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        HessianOutput hessianOutput = new HessianOutput(baos);
        hessianOutput.writeObject(o);
        hessianOutput.flush();
        return baos.toByteArray();
    }

    public static Object Hessian_Deserial(byte[] bytes) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        HessianInput hessianInput = new HessianInput(bais);
        Object o = hessianInput.readObject();
        return o;
    }
}
