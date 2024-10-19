package com.anasajimuhammed.expensemanagerjava.repository;

import com.anasajimuhammed.expensemanagerjava.model.UserDataModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDataModel, Long> {
    UserDataModel findByEmail(String email);
}
