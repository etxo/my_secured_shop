package de.telran.my_secured_shop.services.jpa;

import de.telran.my_secured_shop.domain.entity.Task;
import de.telran.my_secured_shop.repository.jpa.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Getter
public class TaskService {

    private TaskRepository repository;
    public Task createTask(String description){
        return repository.save(new Task(description));
    }
}
