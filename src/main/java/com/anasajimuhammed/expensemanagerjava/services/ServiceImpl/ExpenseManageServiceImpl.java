package com.anasajimuhammed.expensemanagerjava.services.ServiceImpl;

import com.anasajimuhammed.expensemanagerjava.model.ExpenseModel;
import com.anasajimuhammed.expensemanagerjava.repository.ExpenseRepository;
import com.anasajimuhammed.expensemanagerjava.services.ExpenseManageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Getter
public class ExpenseManageServiceImpl implements ExpenseManageService {

    ExpenseRepository expenseRepository;

    @Autowired
    public void setExpenseRepository(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    @Override
    public Optional<List<ExpenseModel>> getAllExpenses() {
        return Optional.of(expenseRepository.findAll());
    }

    @Override
    public Page<ExpenseModel> getAllExpensesPaginated(Pageable pageable) {
        return expenseRepository.findAll(pageable);
    }

    @Override
    public Optional<ExpenseModel> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    @Override
    public Optional<ExpenseModel> createExpense(ExpenseModel expenseModel) {
        System.out.println("service createExpense: " + expenseModel);
        try{
            return Optional.of(expenseRepository.save(expenseModel));
        } catch (Exception e) {
            System.out.println("Exception");
            return Optional.ofNullable(null);
        }
    }

    @Override
    public Optional<ExpenseModel> updateExpense(ExpenseModel expenseModel) {
        System.out.println("service updateExpense: " + expenseModel);
        try{
            return Optional.of(expenseRepository.save(expenseModel));
        } catch (Exception e) {
            return Optional.ofNullable(null);
        }
    }

    @Override
    public boolean deleteExpense(Long id) {
        try{
            expenseRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
