package com.sahil.SplitwiseApp.service.expenses;

import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.repo.ExpensesRepo;
import com.sahil.SplitwiseApp.service.debtUsers.IDebtUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService implements IExpensesService{

    @Autowired
    private ExpensesRepo repo;

    @Autowired
    private IDebtUsersService debtUsersService;

    public Expenses addExpense(Expenses expense){
        return repo.save(expense);
    }

    public Expenses addExpenseAndDebtUsers(int userId,Expenses expense){
        expense.setUserId(userId);
        Expenses addedExpense = addExpense(expense);
        for(DebtUsers debtUser : expense.getDebtUsersList()){
            debtUser.setExpense(addedExpense);
            debtUsersService.addDebtUser(debtUser);
        }
        return expense;
    }

    public Expenses updateExpense(Expenses expenses){
        return repo.save(expenses);
    }

    public Expenses updateExpenseById(int expenseId,int userId,Expenses expense){
        expense.setId(expenseId);
        expense.setUserId(userId);
        return updateExpense(expense);
    }

    public Optional<Expenses> getExpenseByExpenseId(int id){
        return repo.findById(id);
    }

    public List<Optional<Expenses>> getExpenseByUserId(int id){
        return repo.findByUserId(id);
    }

    public void updateExpenseById(int expenseId, String title, BigDecimal amount){
        Optional<Expenses> optionalExpense = getExpenseByExpenseId(expenseId);
        if(optionalExpense.isPresent()){
            Expenses existingExpense = optionalExpense.get();
            existingExpense.setAmount(amount);
            existingExpense.setTitle(title);
            repo.save(existingExpense);
        }
    }

    public void deleteExpenseById(int expenseId){
        repo.deleteById(expenseId);
    }
}
