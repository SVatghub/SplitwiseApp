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
    Optional<Expenses> getExpenseByExpenseId(int userId,int expenseId);
    List<Optional<Expenses>> getExpenseByUserId(int id);
    void deleteExpenseById(int userId,int expenseId);
}
