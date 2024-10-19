package com.anasajimuhammed.expensemanagerjava.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "expenseUser")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    private String email;
    private String password;

}
