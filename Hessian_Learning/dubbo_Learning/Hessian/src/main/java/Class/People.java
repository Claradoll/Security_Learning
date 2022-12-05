package Class;

public class People {
    int id;
    String name;

    public int getId() {
        System.out.println("Student getId call");
        return id;
    }

    public void setId(int id) {
        System.out.println("Student setId call");
        this.id = id;
    }

    public String getName() {
        System.out.println("Student getName call");
        return name;
    }

    public void setName(String name) {
        System.out.println("Student setName call");
        this.name = name;
    }
}