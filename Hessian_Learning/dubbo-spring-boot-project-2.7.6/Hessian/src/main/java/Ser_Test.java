import java.io.*;

public class Ser_Test implements Serializable {

    public static <T> byte[] serialize(T t) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(t);
        System.out.println(bao.toString());
        return bao.toByteArray();
    }

    public static <T> T deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bai = new ByteArrayInputStream(bytes);
        ObjectInputStream ois  =new ObjectInputStream(bai);
        return (T) ois.readObject();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Person person = new Person();
        person.setAge(18);
        person.setName("Feng");

        byte[] s=serialize(person);
        System.out.println(s);
        System.out.println((Person) deserialize(s));
    }
}
