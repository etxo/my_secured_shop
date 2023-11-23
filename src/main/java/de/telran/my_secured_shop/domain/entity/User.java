package de.telran.my_secured_shop.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    @Min(18)
    @Max(119)
    private int age;

    @Column(name = "email")
    @Pattern(regexp = "^[\\w+_.-]+@(.+)$")
    private String email;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    public User(int id, String name, int age, String email){
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
    }

}
