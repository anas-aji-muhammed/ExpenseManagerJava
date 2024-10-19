package com.anasajimuhammed.expensemanagerjava.dto;

import com.anasajimuhammed.expensemanagerjava.model.ExpenseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExpenseAppResponse {
    String message;
    List<ExpenseModel> data;
}
