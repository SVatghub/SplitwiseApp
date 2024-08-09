package com.sahil.SplitwiseApp.service.expenses;

import com.sahil.SplitwiseApp.model.Expenses;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IExpensesService {
    Expenses addExpense(Expenses expense);
    Expenses addExpenseAndDebtUsers(int userId,Expenses expense);
    Expenses updateExpense(Expenses expenses);
    Expenses updateExpenseById(int expenseId,int userId,Expenses expense);
    Optional<Expenses> getExpenseByExpenseId(int id);
    List<Optional<Expenses>> getExpenseByUserId(int id);
    void updateExpenseById(int expenseId, String title, BigDecimal amount);
    void deleteExpenseById(int expenseId);
}
