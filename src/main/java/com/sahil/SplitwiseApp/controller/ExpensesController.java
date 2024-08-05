package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(ApiConstants.BASE_URL_EXPENSES)
public class ExpensesController {

    @Autowired
    private ExpensesService service;

    @Autowired
    private DebtUsersController debtUsersController;

    @PostMapping
    public Expenses addExpense(@PathVariable("user-Id") int userId,@RequestBody Expenses expense){
        expense.setUserId(userId);
        Expenses addedExpense = service.addExpense(expense);

        for(DebtUsers debtUser : expense.getDebtUsersList()){
            debtUser.setExpense(addedExpense);
            debtUsersController.addDebtUser(debtUser);
        }

        return expense;
    }

    @PutMapping("/{expense-Id}")
    public Expenses updateExpenseById(@PathVariable("expense-Id") int expenseId, @PathVariable("user-Id") int userId, @RequestBody Expenses expense) {
        expense.setId(expenseId);
        expense.setUserId(userId);
        return service.updateExpense(expense);
    }

    @GetMapping("/{expense-Id}")
    public Optional<Expenses> getExpenseByExpenseId(@PathVariable("expense-Id") int expenseId){
        return service.getExpenseByExpenseId(expenseId);
    }

    @GetMapping
    public List<Optional<Expenses>> getExpenseByUserId(@PathVariable("user-Id") int userId){
        return service.getExpenseByUserId(userId);
    }

    @DeleteMapping("/{expense-Id}")
    public void deleteExpenseByExpenseId(@PathVariable("expense-Id") int expenseId){
        service.deleteExpenseById(expenseId);
    }
}
