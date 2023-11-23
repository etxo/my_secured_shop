package de.telran.my_secured_shop.repository.jpa;

import de.telran.my_secured_shop.domain.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    void deleteByName(String name);

   @Query(value = "select sum(price) from product;", nativeQuery = true)
   double getTotalPrice();
    @Query(value = "select avg(price) from product;", nativeQuery = true)
    double getAvgPrice();
}
