package com.anasajimuhammed.expensemanagerjava.controller;

import com.anasajimuhammed.expensemanagerjava.dto.ExpenseAppResponse;
import com.anasajimuhammed.expensemanagerjava.model.ExpenseModel;
import com.anasajimuhammed.expensemanagerjava.services.ExpenseManageService;
import com.anasajimuhammed.expensemanagerjava.utils.ResponseConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@Getter
@RestController()
public class ExpenseController {
    ExpenseManageService expenseManageService;

    @Autowired
    public void setExpenseManageService(ExpenseManageService expenseManageService) {
        this.expenseManageService = expenseManageService;
    }

    @GetMapping("/get-expenses")
    public ResponseEntity<ExpenseAppResponse> expenses() {
        System.out.println("expense app controller called");
        var expenses = expenseManageService.getAllExpenses();
        if(expenses.isPresent()){
            ExpenseAppResponse expenseAppResponse = new ExpenseAppResponse();
            expenseAppResponse.setData(expenses.get());
            expenseAppResponse.setMessage("Success");
            return  ResponseEntity.ok(expenseAppResponse);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


    @GetMapping("/get-expenses-paginated")
    public ResponseEntity<Page<ExpenseModel>> getAllExpenses(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateAdded") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Sort.Direction direction = sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));

        Page<ExpenseModel> expensePage = expenseManageService.getAllExpensesPaginated(pageable);

        return ResponseEntity.ok(expensePage);
    }

    @PostMapping(value = "/create-expense")
    public ResponseEntity<ExpenseAppResponse> createExpense(@RequestBody ExpenseModel expenseModel) {
        System.out.println("createExpense controller called: with data " + expenseModel.toString());

        Optional<ExpenseModel> expenses;
        ExpenseAppResponse expenseAppResponse = new ExpenseAppResponse();
        if(expenseModel.getId() == null){
            expenses = expenseManageService.createExpense(expenseModel);
        }
        else{
            expenses = expenseManageService.updateExpense(expenseModel);
        }
        if(expenses.isPresent()){
            expenseAppResponse.setMessage(ResponseConstants.SUCCESS);
            return  ResponseEntity.ok(expenseAppResponse);
        }
        expenseAppResponse.setMessage(ResponseConstants.WENT_WRONG);
        System.out.println("createExpense controller called: response message" + expenseAppResponse.getMessage());

        return new ResponseEntity<>( HttpStatus.NOT_MODIFIED);

    }

    @DeleteMapping("delete-expense")
    public ResponseEntity<ExpenseAppResponse> deleteExpense(@RequestParam Long id) {
        ExpenseAppResponse expenseAppResponse = new ExpenseAppResponse();
        if (expenseManageService.deleteExpense(id)) {
            expenseAppResponse.setMessage(ResponseConstants.SUCCESS);
            return ResponseEntity.ok(expenseAppResponse);
        } else {
            expenseAppResponse.setMessage(ResponseConstants.WENT_WRONG);
            return new ResponseEntity<>(expenseAppResponse, HttpStatus.NOT_MODIFIED);
        }
    }
}
