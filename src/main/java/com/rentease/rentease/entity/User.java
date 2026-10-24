package com.rentease.rentease.entity;



import jakarta.persistence.*;
import lombok.Setter;
import lombok.Getter;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(unique = true,nullable = false)
    private String email;


    @Column(nullable = false)

    private String password;


    @Enumerated(EnumType.STRING)
    private Role role;

    public User(){

    }

   public User(String name, String email, String password, Role role) {
       this.name = name;
       this.email = email;
       this.password = password;
       this.role = role;
   }


}
