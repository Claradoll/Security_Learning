package Tools;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class Base64_Class {
    public static void main(String[] args) throws IOException {
        byte[] data = Base64.getEncoder().encode(Files.readAllBytes(Paths.get("ser.bin")));
        System.out.println(new String(data).length());
        System.out.println(new String(data));
    }

}
