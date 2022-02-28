package day11.Task;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

/*
*  Write a Cat class with a name, weight, and age. Insert some random values in the Cat table.
Write a code using criteria API to return the list of cats in the table such that the name starts with
the letter m with a weight greater than 2. The output of the criteria API should be ordered by
age in ascending order.
*/
@Entity
@Table(name = "cat")
public class Cat {
    @Id
    @GeneratedValue(generator = "custom")
    @GenericGenerator(name = "custom", strategy = "day9.IdGenerator")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Cat_id")
    private int id;
    @Column(name = "cat_name", nullable = false, length = 100)
    private String name;
    @Column(name = "weight", nullable = false)
    private double weight;
    @Column(name = "age", nullable = false)
    private double age;

    public Cat(String name, double weight, double age) {
        this.name = name;
        this.weight = weight;
        this.age = age;
    }

    public Cat() {
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}
