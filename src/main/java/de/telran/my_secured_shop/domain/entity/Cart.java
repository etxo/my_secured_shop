package de.telran.my_secured_shop.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@Getter@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private int id;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;
    public Cart(User user){
        this.user = user;
    }

    public void addProduct(Product product) {
        products.add(new Product(product.getId(),
                                    product.getName(),
                                    product.getPrice()));
    }

    public double getTotalPrice() {

        return products.stream()
                .mapToDouble(x -> x.getPrice())
                .sum();
    }

    public double getAvgPrice() {

        return products.stream().mapToDouble(x -> x.getPrice())
                .average()
                .orElse(0);
    }

    public void deleteProduct(int id) {

        products.removeIf(x -> x.getId() == id);
    }

    public void clear() {
        products.clear();
    }
    public List<Product> getProducts(){
        return new ArrayList<>(products);
    }
}
