package com.anasajimuhammed.expensemanagerjava.repository;
import com.anasajimuhammed.expensemanagerjava.model.ExpenseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<ExpenseModel, Long> {
    Page<ExpenseModel> findAll(Pageable pageable);
}
