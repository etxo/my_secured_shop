package de.telran.my_secured_shop.repository.jpa;

import de.telran.my_secured_shop.domain.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Integer> {
}
