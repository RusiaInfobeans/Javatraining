package day11.Task;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="product_records")
public class Product {
    @Id
//    @GeneratedValue(generator = "custom")
//    @GenericGenerator(name = "custom", strategy = "day9.IdGenerator")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;
    @Column(name = "product_name", nullable = false, length = 100)
    private String name;
    @Column(name = "product price", nullable = false)
    private double price;

    public Product() {
    }
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public void setStore(Store store) {
    }
}
