package Class;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Student extends People implements Serializable {
    private static final long serialVersionUID = 1L;

    private static Student student = new Student(111, "xxx", "ggg");
    private transient String gender;
    private Map<String, Class<Object>> innerMap;
    private List<Student> friends;

    public void setFriends(List<Student> friends) {
        System.out.println("Student setFriends call");
        this.friends = friends;
    }

    public void getFriends(List<Student> friends) {
        System.out.println("Student getFriends call");
        this.friends = friends;
    }


    public Map getInnerMap() {
        System.out.println("Student getInnerMap call");
        return innerMap;
    }

    public void setInnerMap(Map innerMap) {
        System.out.println("Student setInnerMap call");
        this.innerMap = innerMap;
    }

    public String getGender() {
        System.out.println("Student getGender call");
        return gender;
    }

    public void setGender(String gender) {
        System.out.println("Student setGender call");
        this.gender = gender;
    }

    public Student() {
        System.out.println("Student default constructor call");
    }

    public Student(int id, String name, String gender) {
        System.out.println("Student custom constructor call");
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    private void readObject(ObjectInputStream ObjectInputStream) {
        System.out.println("Student readObject call");
    }

    private Object readResolve() {
        System.out.println("Student readResolve call");

        return student;
    }

    @Override
    public int hashCode() {
        System.out.println("Student hashCode call");
        return super.hashCode();
    }

    @Override
    protected void finalize() throws Throwable {
        System.out.println("Student finalize call");

        super.finalize();
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", innerMap=" + innerMap +
                ", friends=" + friends +
                '}';
    }
}