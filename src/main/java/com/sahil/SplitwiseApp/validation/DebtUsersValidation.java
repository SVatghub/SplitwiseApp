package com.sahil.SplitwiseApp.validation;

import com.sahil.SplitwiseApp.exceptions.InValidIsSettledException;
import com.sahil.SplitwiseApp.exceptions.ResourceNotFoundException;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.repo.DebtUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DebtUsersValidation extends ExpensesValidation{

    @Autowired
    DebtUsersRepo debtUsersRepo;

    private void isValidIsSettled(boolean isSettled){
        if(!(isSettled == true || isSettled == false))
            throw new InValidIsSettledException("isSettled can take only true or false");
    }

    public void validateDebtUserByUserIdAndExpenseId(int userId,int expenseId){
        if(debtUsersRepo.getDebtUserByExpenseIdAndUserId(userId,expenseId)==null)
            throw new ResourceNotFoundException("No Debts for the user with id " + userId + " and expense id " + expenseId);
    }

    public void isValidDebtUser(DebtUsers debtUser){
        validateAmount(debtUser.getDebtAmount());
        existsById(debtUser.getUserId());
        isValidIsSettled(debtUser.isSettled());
    }
}
