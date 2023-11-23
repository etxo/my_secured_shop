package de.telran.my_secured_shop.services.interfaces;

import java.util.List;
import de.telran.my_secured_shop.domain.entity.Product;

public interface ProductService {

    List<Product> getAll();
    Product getById(int id);
    void add(Product product);
    int getCount();
    double getTotalPrice();
    double getAvgPrice();
    void deleteById(int id);
    void deleteByName(String name);

}
