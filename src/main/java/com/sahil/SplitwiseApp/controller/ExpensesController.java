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
    public Expenses addExpense(@PathVariable("userId") int userId,@RequestBody Expenses expense){
        expense.setUserId(userId);
        Expenses addedExpense = service.addExpense(expense);

        for(DebtUsers debtUser : expense.getDebtUsersList()){
            debtUser.setExpense(addedExpense);
            debtUsersController.addDebtUser(debtUser);
        }

        return expense;
    }

    @PutMapping("/{expenseId}")
    public Expenses updateExpenseById(@PathVariable int expenseId, @PathVariable int userId, @RequestBody Expenses expense) {
        expense.setId(expenseId);
        expense.setUserId(userId);
        return service.updateExpense(expense);
    }

    @GetMapping("/{expenseId}")
    public Optional<Expenses> getExpenseByExpenseId(@PathVariable("expenseId") int expenseId){
        return service.getExpenseByExpenseId(expenseId);
    }

    @GetMapping
    public List<Optional<Expenses>> getExpenseByUserId(@PathVariable("userId") int userId){
        return service.getExpenseByUserId(userId);
    }

    @DeleteMapping("/{expenseId}")
    public void deleteExpenseByExpenseId(@PathVariable int expenseId){
        service.deleteExpenseById(expenseId);
    }
}
