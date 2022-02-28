package day11.Task;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store {
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private Set<Product1> products= new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GenericGenerator(strategy = "com.company.day8.demo.CustomIdGenerator", name = "custom-generator")
    private Integer id;
    private String name;
    private String address;

    public Store() {
    }

    public Set<Product1> getProducts() {
        return products;
    }
    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "Store{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", products=" + products +
                '}';
    }
}