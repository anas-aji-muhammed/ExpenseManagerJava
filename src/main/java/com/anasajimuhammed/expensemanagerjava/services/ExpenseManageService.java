package com.anasajimuhammed.expensemanagerjava.services;

import com.anasajimuhammed.expensemanagerjava.model.ExpenseModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExpenseManageService {
    public Optional<List<ExpenseModel>> getAllExpenses();
    public Page<ExpenseModel> getAllExpensesPaginated(Pageable pageable);
    public Optional<ExpenseModel> getExpenseById(Long id);
    public Optional<ExpenseModel> createExpense(ExpenseModel expenseModel);
    public Optional<ExpenseModel> updateExpense(ExpenseModel expenseModel);
    public boolean deleteExpense(Long id);

}
