package Hessian;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import Class.Student;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.sql.SQLData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SerialTest {

    public static <T> byte[] serialize(T t) {
        byte[] data = null;
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            HessianOutput output = new HessianOutput(os);
            output.writeObject(t);
            data = os.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static <T> T deserialize(byte[] data) {
        if (data == null) {
            return null;
        }
        Object result = null;
        try {
            ByteArrayInputStream is = new ByteArrayInputStream(data);
            HessianInput input = new HessianInput(is);
            result = input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) result;
    }

    public static void main(String[] args) {
        int id = 111;
        String name = "hessian";
        String gender = "boy";

        Map innerMap = new HashMap<String, Class<Object>>();
        innerMap.put("1", ObjectInputStream.class);
        innerMap.put("2", SQLData.class);

        Student friend = new Student(222, "hessian1", "boy");
        List friends = new ArrayList<Student>();
        friends.add(friend);

        Student stu = new Student();
        stu.setId(id);
        stu.setName(name);
        stu.setGender(gender);
        stu.setInnerMap(innerMap);
        stu.setFriends(friends);

        System.out.println("---------------hessian serialize----------------");
        byte[] obj = serialize(stu);
        System.out.println(new String(obj));

        System.out.println("---------------hessian deserialize--------------");
        Student student = deserialize(obj);
        System.out.println(student);
    }
}