package  day11.Task;
import jakarta.persistence.*;

@Entity
@Table(name = "product1")
public class Product1{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    public Product1(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    public Product1() {
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

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", store=" + store +
                '}';
    }
}