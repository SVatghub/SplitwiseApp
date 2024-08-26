package com.sahil.SplitwiseApp.validation;

import com.sahil.SplitwiseApp.exceptions.*;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.repo.ExpensesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Optional;
import java.util.List;

@Component
public class ExpensesValidation extends UsersValidation{

    @Autowired
    private ExpensesRepo expensesRepo;

    public void validateExpense(Expenses expense){
        validateAmount(expense.getAmount());
        validateTitle(expense.getTitle());
        validateDebtUsersList(expense.getDebtUsersList());
        validateAmountBreakdown(expense);
    }

    private void validateAmountBreakdown(Expenses expense) {
        BigDecimal totSum = expense.getAmount();
        BigDecimal totDebtSum = BigDecimal.ZERO;

        for (DebtUsers debtUsers : expense.getDebtUsersList())
            totDebtSum = totDebtSum.add(debtUsers.getDebtAmount());

        if (totSum.compareTo(totDebtSum) <= 0)
            throw new InvalidAmountBreakDownException("Sum of debt amounts need to be smaller than the total amount");
    }

    private void validateTitle(String title){
        if(title == null)
            throw new NullFieldException("title cannot be null");
        else if(title.isEmpty())
            throw new EmptyInputException("Title cannot be empty");
    }

    private void validateDebtUsersList(List<DebtUsers> debtUsersList){
        if(debtUsersList == null)
            throw new NullFieldException("Debt users can not be null");
        else if(debtUsersList.isEmpty())
            throw new EmptyInputException("There has to be at least 1 user in debt");
    }

    public void validateAmount(BigDecimal amount){
        if(amount == null)
            throw new NullFieldException("amount can not be null");
        else if(amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new InvalidAmountException("Expense amount must be greater than 0");
    }


    public void isEmptyExpense(Optional<Expenses> expense){
        if(expense.isEmpty()) {
            throw new ResourceNotFoundException("No expense found with this expense-id");
        }
    }

    public void validateExpenseByExpenseId(int expenseId){
        Optional<Expenses> optionalExpense = expensesRepo.findById(expenseId);
        if(optionalExpense.isEmpty())
            throw new ResourceNotFoundException("Could not find expense with the id : " + expenseId);
    }
}
