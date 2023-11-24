package de.telran.my_secured_shop.services.jpa;

import de.telran.my_secured_shop.domain.entity.Product;
import de.telran.my_secured_shop.domain.entity.Task;
import de.telran.my_secured_shop.repository.ProductRepository;
import de.telran.my_secured_shop.services.interfaces.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Getter
public class JpaProductService implements ProductService {

    private final Logger LOGGER = LogManager.getLogger(JpaProductService.class);

    private ProductRepository repository;
    private TaskService taskService;

    public static Task lastTask;
    @Override
    public List<Product> getAll() {

        //ScheduleExecutor.executeScheduledTask(task);
        return new ArrayList<>(repository.findAll());
    }

    @Override
    public Product getById(int id) {

        //LOGGER.log(Level.INFO, "product with id {} was required", id);
        //LOGGER.log(Level.WARN, "product with id {} was required", id);
        //LOGGER.log(Level.ERROR, "product with id {} was required", id);

        LOGGER.info("product with id {} was required", id);
        LOGGER.warn("product with id {} was required", id);
        LOGGER.error("product with id {} was required", id);

        return repository.findById(id).orElse(null);
    }

    @Override
    public void add(Product product) {
        repository.save(new Product(product.getId(),
                                        product.getName(),
                                        product.getPrice()));
        lastTask = taskService.createTask("Product " + product.getName() + " was added.");
        // the following line is for test purpose only.
        product.setId(777);
    }

    @Override
    public int getCount() {
        return (int)repository.count();
    }

    @Override
    public double getTotalPrice() {
        return repository.getTotalPrice();
    }

    @Override
    public double getAvgPrice() {
        return repository.getAvgPrice();
    }

    @Override
    public void deleteById(int id) {
        //Task task = taskService.createTask("Attempt to delete product with id" + id);
        //ScheduleExecutor.executeScheduledTaskOnes(task);
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) {
        repository.deleteByName(name);
    }
}
