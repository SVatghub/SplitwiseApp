package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.service.expenses.IExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiConstants.BASE_URL_EXPENSES)
public class ExpensesController {

    @Autowired
    private IExpensesService service;

    @PostMapping
    public Expenses addExpenseAndDebtUsers(@PathVariable("user-Id") int userId, @RequestBody Expenses expense){
        return service.addExpenseAndDebtUsers(userId, expense);
    }

    @PutMapping("/{expense-Id}")
    public Expenses updateExpenseById(@PathVariable("expense-Id") int expenseId, @PathVariable("user-Id") int userId, @RequestBody Expenses expense) {
        return service.updateExpenseById(expenseId,userId,expense);
    }

    @GetMapping("/{expense-Id}")
    public Optional<Expenses> getExpenseByExpenseId(@PathVariable("expense-Id") int expenseId,@PathVariable("user-Id") int userId){
        return service.getExpenseByExpenseId(userId,expenseId);
    }

    @GetMapping
    public List<Optional<Expenses>> getExpenseByUserId(@PathVariable("user-Id") int userId){
        return service.getExpenseByUserId(userId);
    }

    @DeleteMapping("/{expense-Id}")
    public void deleteExpenseByExpenseId(@PathVariable("expense-Id") int expenseId,@PathVariable("user-Id") int userId){
        service.deleteExpenseById(userId,expenseId);
    }
}
