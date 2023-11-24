package de.telran.my_secured_shop.services.jpa;

import de.telran.my_secured_shop.domain.entity.Cart;
import de.telran.my_secured_shop.domain.entity.User;
import de.telran.my_secured_shop.repository.CartRepository;
import de.telran.my_secured_shop.repository.UserRepository;
import de.telran.my_secured_shop.repository.ProductRepository;
import de.telran.my_secured_shop.services.interfaces.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class JpaUserService implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(JpaProductService.class);

    private UserRepository repository;
    private CartRepository cartRepo;
    private ProductRepository productRepo;

    @Override
    public List<User> getAll() {

        return new ArrayList<>(repository.findAll());
    }

    @Override
    public User getById(int id) {

        LOGGER.info("customer with id {} was required.", id);
        LOGGER.warn("customer with id {} was required.", id);
        LOGGER.error("customer with id {} was required.", id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(User user) {

        User savedUser = repository.save(
                new User(0,
                        user.getName(),
                        user.getAge(),
                        user.getEmail()));
        cartRepo.save(new Cart(savedUser));
    }

    @Override
    public int getCount() {
        return (int)repository.count();
    }

    @Transactional
    @Override
    public void addToCartById(int userId, int productId) {
        LOGGER.debug("calling method  addToCartById() with parameter userId {} and productId {}", userId, productId);
        User user = getById(userId);
        Cart cart = user.getCart();
        cart.addProduct(productRepo.getById(productId));
        //cartRepo.save((JpaCart) cart);
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }

    @Override
    public double getTotalPriceById(int id) {
        return getById(id).getCart().getTotalPrice();
    }

    @Override
    public double getAvgPriceById(int id) {
        return getById(id).getCart().getAvgPrice();
    }

    @Override
    public void deleteFromCartById(int customerId, int productId) {
        getById(customerId).getCart().deleteProduct(productId);
    }

    @Override
    public void clearCartById(int id) {
        getById(id).getCart().clear();
    }
}
