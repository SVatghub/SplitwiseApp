package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.service.ExpensesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@RestController
@RequestMapping("SplitwiseApp")
public class ExpensesController {

    @Autowired
    private ExpensesService service;

    @Autowired
    private DebtUsersController debtUsersController;

    @PostMapping("/users/{userId}/expenses")
    public void addExpense(@PathVariable("userId") int userId,@RequestBody Expenses expense){
        expense.setUserId(userId);
        Expenses addedExpense = service.addExpense(expense);

        for(DebtUsers debtUser : expense.getDebtUsersList()){
            debtUser.setExpense(addedExpense);
            debtUsersController.addDebtUser(debtUser);
        }
    }

    // needs to be checked and updated NOT WORKING
    @PutMapping("users/{userId}/expenses/{expenseId}")
    public void updateExpenseById(@PathVariable int expenseId,@PathVariable int userId,@RequestBody Expenses expense){
        Optional<Expenses> optionalExpenses = service.getExpenseByExpenseId(expenseId);
        if(optionalExpenses.isPresent()){
            Expenses existingExpense = optionalExpenses.get();

            for(DebtUsers debtUser :existingExpense.getDebtUsersList()){
                debtUsersController.deleteDebtUserByExpenseIdAndUserId(debtUser.getUserId(),debtUser.getExpense().getId());
            }

            for(DebtUsers debtUser :expense.getDebtUsersList()){
                debtUsersController.addDebtUser(debtUser);
            }
            // need to check this working
        }
    }

    @GetMapping("users/{userId}/expenses/{expenseId}")
    public Optional<Expenses> getExpenseByExpenseId(@PathVariable("expenseId") int expenseId){
        return service.getExpenseByExpenseId(expenseId);
    }

    @GetMapping("/users/{userId}/expenses")
    public List<Optional<Expenses>> getExpenseByUserId(@PathVariable("userId") int userId){
        return service.getExpenseByUserId(userId);
    }

    @DeleteMapping("/users/{userId}/expenses/{expenseId}")
    public void deleteExpenseByExpenseId(@PathVariable int expenseId){
        service.deleteExpenseById(expenseId);
    }
}
