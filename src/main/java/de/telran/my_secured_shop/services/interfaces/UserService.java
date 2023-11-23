package de.telran.my_secured_shop.services.interfaces;

import de.telran.my_secured_shop.domain.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(int id);
    void add(User customer);
    void deleteByName(String name);
    int getCount();
    double getTotalPriceById(int id);
    double getAvgPriceById(int id);
    void addToCartById(int customerId, int productId);
    void deleteFromCartById(int customerId, int productId);
    void clearCartById(int id);
}
