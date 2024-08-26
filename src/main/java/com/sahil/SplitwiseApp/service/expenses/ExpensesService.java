package com.sahil.SplitwiseApp.service.expenses;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.DebtUsersDTO;
import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.ExpenseSettlementStatusDTO;
import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.UserNotSettledDTO;
import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.UserSettledDTO;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.repo.ExpensesRepo;
import com.sahil.SplitwiseApp.service.debtUsers.IDebtUsersService;
import com.sahil.SplitwiseApp.service.users.UsersService;
import com.sahil.SplitwiseApp.validation.DebtUsersValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ExpensesService implements IExpensesService{

    @Autowired
    private ExpensesRepo repo;

    @Autowired
    private IDebtUsersService debtUsersService;

    @Autowired
    private DebtUsersValidation debtUsersValidation;

    @Autowired
    private UsersService usersService;

    public Expenses addExpense(Expenses expense){
        return repo.save(expense);
    }

    public Expenses updateExpense(Expenses expenses){
        return repo.save(expenses);
    }

    public Expenses addExpenseAndDebtUsers(int userId,Expenses expense){
        debtUsersValidation.existsById(userId);
        expense.setUserId(userId);

        debtUsersValidation.validateExpense(expense);
        Expenses addedExpense = addExpense(expense);

        for(DebtUsers debtUsers: expense.getDebtUsersList()){
            debtUsersValidation.existsById(debtUsers.getUserId());
            debtUsersValidation.validateAmount(debtUsers.getDebtAmount());
        }

        for(DebtUsers debtUser : expense.getDebtUsersList()){
            debtUser.setExpense(addedExpense);
            debtUsersService.addDebtUser(debtUser);
        }

        return expense;
    }

    public Expenses updateExpenseById(int expenseId,int userId,Expenses expense){
        debtUsersValidation.existsById(userId);
        debtUsersValidation.validateExpenseByExpenseId(expenseId);
        debtUsersValidation.validateExpense(expense);

        for(DebtUsers debtUsers: expense.getDebtUsersList()){
            debtUsersValidation.existsById(debtUsers.getUserId());
            debtUsersValidation.validateAmount(debtUsers.getDebtAmount());
        }

        expense.setId(expenseId);
        expense.setUserId(userId);
        return updateExpense(expense);
    }

    public Optional<Expenses> getExpenseByExpenseId(int userId,int expenseId){
        debtUsersValidation.existsById(userId);
        debtUsersValidation.validateExpenseByExpenseId(expenseId);

        Optional<Expenses> expense =  repo.findById(expenseId);
        debtUsersValidation.isEmptyExpense(expense);
        return expense;
    }

    public List<Optional<Expenses>> getExpenseByUserId(int id){
        debtUsersValidation.existsById(id);
        return repo.findByUserId(id);
    }

    public void deleteExpenseById(int userId,int expenseId){
        debtUsersValidation.existsById(userId);
        debtUsersValidation.validateExpenseByExpenseId(expenseId);

        Optional<Expenses> expense = getExpenseByExpenseId(userId,expenseId);
        debtUsersValidation.isEmptyExpense(expense);
        repo.deleteById(expenseId);
    }

    public ExpenseSettlementStatusDTO getExpenseSettlementsStatus(int expenseId){
        return ExpenseSettlementStatusDTO.builder()
                .userSettledDTOList(getSettledUsersByExpenseId(expenseId))
                .userNotSettledDTOList(getNotSettledUsersByExpenseId(expenseId))
                .build();
    }

    private List<UserSettledDTO> getSettledUsersByExpenseId(int expenseId) {
        List<DebtUsersDTO> debtUsers = debtUsersService.getDebtUsersByExpenseId(expenseId);
        List<UserSettledDTO> userSettledDTOs = new java.util.ArrayList<>(List.of());
        for(DebtUsersDTO debtUsersDTO : debtUsers){
            if(debtUsersDTO.isSettled()){
                userSettledDTOs.add( UserSettledDTO.builder()
                        .settledAt(debtUsersDTO.getUpdatedAt())
                        .expenseId(expenseId)
                        .userId(debtUsersDTO.getUserId())
                        .settlementAmount(debtUsersDTO.getDebtAmount())
                        .userName(usersService.getUserById(debtUsersDTO.getUserId()).getName())
                        .build());
            }
        }
        return userSettledDTOs;
    }

    private List<UserNotSettledDTO> getNotSettledUsersByExpenseId(int expenseId) {
        List<DebtUsersDTO> debtUsers = debtUsersService.getDebtUsersByExpenseId(expenseId);
        List<UserNotSettledDTO> userNotSettledDTOS = new java.util.ArrayList<>(List.of());
        for(DebtUsersDTO debtUsersDTO : debtUsers){
            if(!debtUsersDTO.isSettled()){
                userNotSettledDTOS.add(UserNotSettledDTO.builder()
                        .expenseId(expenseId)
                        .userId(debtUsersDTO.getUserId())
                        .settlementAmount(debtUsersDTO.getDebtAmount())
                        .userName(usersService.getUserById(debtUsersDTO.getUserId()).getName())
                        .build());
            }
        }
        return userNotSettledDTOS;
    }
}
