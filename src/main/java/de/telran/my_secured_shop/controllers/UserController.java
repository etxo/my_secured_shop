package de.telran.my_secured_shop.controllers;

import de.telran.my_secured_shop.domain.entity.User;
import de.telran.my_secured_shop.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> getAll() {

        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable int id){
        return ResponseEntity.ok(userService.getById(id));
    }
    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody User user){
        userService.add(user);
        return ResponseEntity.ok("added");
    }
    @GetMapping("/count")
    public ResponseEntity getCount() {

        return ResponseEntity.ok(userService.getCount());
    }
    @GetMapping("/total/{id}")
    public ResponseEntity getTotalPrice(@PathVariable int id) {

        return ResponseEntity.ok(userService.getTotalPriceById(id));
    }

    @GetMapping("/average/{id}")
    public ResponseEntity getAvgPrice(@PathVariable int id) {

        return ResponseEntity.ok(userService.getAvgPriceById(id));
    }

    @PostMapping("/{userId}/{productId}")
    public String addToCart(@PathVariable int userId, @PathVariable int productId) {
        userService.addToCartById(userId, productId);
        return "added";
    }

    @DeleteMapping("/{userId}/{productId}")
    public String deleteFromCart(@PathVariable int userId, @PathVariable int productId) {
        userService.deleteFromCartById(userId, productId);
        return "deleted";
    }

    @DeleteMapping("/name/{name}")
    public String delete(@PathVariable String name) {

        userService.deleteByName(name);
        return "deleted";
    }
    @DeleteMapping("/clear/{userId}")
    public String clearCart(@PathVariable int userId) {

        userService.clearCartById(userId);
        return "deleted";
    }
}
