package Hessian2;

import Tools.Hessian2_Tools;
import com.alibaba.com.caucho.hessian.io.Hessian2Input;
import com.alibaba.com.caucho.hessian.io.Hessian2Output;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

public class Hessian2_Test implements Serializable {

    public static <T> byte[] serialize(T o) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(bao);
        output.writeObject(o);
        return bao.toByteArray();
    }

    public static <T> T deserialize(byte[] bytes) throws IOException {
        ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
        Hessian2Input input = new Hessian2Input(bai);
        System.out.println(input);
        Object o = input.readObject();
        return (T) o;
    }

    public static void main(String[] args) throws IOException {
        String s= "a";
        byte[] payload = Hessian2_Tools.Hessian2_Serial(s);

        System.out.println(Hessian2_Tools.Hessian2_Deserial(payload));


    }

}
