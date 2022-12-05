package Tools;

import java.io.*;

public class Serial {
    public static void Serialize(Object o) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ser.bin"));
        oos.writeObject(o);
    }

    public static Object DeSerialize(String s) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(s));
        return ois.readObject();
    }
}
