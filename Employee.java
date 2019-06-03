import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class Employee {



    public static void main(String[] args) {
        List<Human> list = new ArrayList<>();
        list.add(new Human(1,"A",new Date()));
        list.add(new Human(0,"C",new Date()));
        list.add(new Human(2,"B",new Date()));

        Collections.sort(list);
        System.out.println(list);

        Collections.sort(list, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        System.out.println(list);
    }
}


class Human implements Comparable<Human> {
    private int id;
    private String name;
    private Date dob;

    public Human(int id, String name, Date dob) {
        this.id = id;
        this.name = name;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    @Override
    public int compareTo(Human o) {
        return this.id-o.id;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                '}';
    }
}