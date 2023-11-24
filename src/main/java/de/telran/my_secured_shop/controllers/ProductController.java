package de.telran.my_secured_shop.controllers;

import de.telran.my_secured_shop.domain.entity.Product;
import de.telran.my_secured_shop.exception_handling.Response;
import de.telran.my_secured_shop.exception_handling.exceptions.FirstTestException;
import de.telran.my_secured_shop.exception_handling.exceptions.SecondTestException;
import de.telran.my_secured_shop.exception_handling.exceptions.ThirdTestException;
import de.telran.my_secured_shop.services.interfaces.ProductService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAll() {

        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable int id){

        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid @NotNull Product product){
        if("test".equals(product.getName())){
            throw new FirstTestException("incorrect product name");
        }
        if("testo".equals(product.getName())){
            throw new SecondTestException("guys! that name is weird!");
        }
        if("teston".equals(product.getName())){
            throw new ThirdTestException("no way! not that type!");
        }
        productService.add(product);
        return ResponseEntity.ok("added");
    }

    @DeleteMapping("/name/{name}")
    public ResponseEntity deleteByName(@PathVariable String name){

        productService.deleteByName(name);
        return ResponseEntity.ok("deleted");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteById(@PathVariable int id){

        productService.deleteById(id);
        return ResponseEntity.ok("deleted");
    }

    @GetMapping("/count")
    public int getCount() {
        return productService.getCount();
    }
    @GetMapping("/total_price")
    public ResponseEntity getTotalPrice(){

        return ResponseEntity.ok(productService.getTotalPrice());
    }
    @GetMapping("/avg_price")
    public ResponseEntity getAvgPrice(){

        return ResponseEntity.ok(productService.getAvgPrice());
    }

    @ExceptionHandler(FirstTestException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Response handleException(FirstTestException e){

        return new Response(e.getMessage());
    }
}
