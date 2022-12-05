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
        Person person = new Person();
        person.setAge(18);
        person.setName("Feng");


        byte[] s = serialize(person);
        System.out.println(s);
        System.out.println((Person) deserialize(s));
    }

}
