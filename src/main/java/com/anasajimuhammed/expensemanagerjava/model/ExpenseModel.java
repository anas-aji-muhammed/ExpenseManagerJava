package com.anasajimuhammed.expensemanagerjava.model;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity(name = "expenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ExpenseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id")
    private Long id;

    private String title;
    private float amount;
    private Date date;
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateAdded;

//    @PrePersist
//    protected void onCreate(){
//        dateAdded =  LocalDateTime.now();
//    }


//    @Override
//    public String toString() {
//        return "Expense{" +
//                "id='" + id + '\'' +
//                ", title='" + title + '\'' +
//                ", amount=" + amount +
//                ", date=" + date +
//                ", description='" + description + '\'' +
//                ", dateAdded=" + dateAdded +
//                '}';
//    }
}

