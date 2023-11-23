package de.telran.my_secured_shop.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "product")
@AllArgsConstructor
@Setter
public class Product {



    private static final Logger LOGGER = LoggerFactory.getLogger(Product.class);
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int id;

    @Column(name = "name")
    @Pattern(regexp = "[A-Za-z]{2,}")
    private String name;

    @Column(name = "price")
    @Min(value = 5)
    @Max(value = 99999)
    private double price;

    public Product() {
        LOGGER.debug("No args constructor of JpaProduct was invoked.");
    }

    public int getId() {
        LOGGER.debug("GetId() method of JpaProduct was invoked.");
        return id;
    }

    public String getName() {
        LOGGER.debug("GetName() method of  JpaProduct was invoked.");
        return name;
    }

    public double getPrice() {
        LOGGER.debug("GetPrice() method of  JpaProduct was invoked.");
        return price;
    }
}
