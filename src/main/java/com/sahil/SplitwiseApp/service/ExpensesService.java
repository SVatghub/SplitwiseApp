package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.repo.ExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService {

    @Autowired
    private ExpensesRepo repo;

    // add expenses and return expenses to reference it to add debtUsers
    public Expenses addExpense(Expenses expense){
        return repo.save(expense);
    }

    // get expense by expense id
    public Optional<Expenses> getExpenseByExpenseId(int id){
        return repo.findById(id);
    }

    // get expenses by user Id
    public List<Optional<Expenses>> getExpenseByUserId(int id){
        return repo.findByUserId(id);
    }

    // update Expense using expense id
    public void updateExpenseById(int expenseId, String title, BigDecimal amount){
        Optional<Expenses> optionalExpense = getExpenseByExpenseId(expenseId);
        if(optionalExpense.isPresent()){
            Expenses existingExpense = optionalExpense.get();
            existingExpense.setAmount(amount);
            existingExpense.setTitle(title);
            repo.save(existingExpense);
        }else{
            // need to update
            System.out.println("in else");
        }
    }

    // Deleting an expense
    public void deleteExpenseById(int expenseId){
        repo.deleteById(expenseId);
    }
}
