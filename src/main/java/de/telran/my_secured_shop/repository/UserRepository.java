package de.telran.my_secured_shop.repository;

import de.telran.my_secured_shop.domain.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Transactional
    void deleteByName(String name);

}
