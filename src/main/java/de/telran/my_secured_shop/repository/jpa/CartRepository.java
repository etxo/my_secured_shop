package de.telran.my_secured_shop.repository.jpa;

import de.telran.my_secured_shop.domain.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> { }
