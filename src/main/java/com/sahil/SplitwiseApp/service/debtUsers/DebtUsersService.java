package com.sahil.SplitwiseApp.service.debtUsers;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.DebtUsersDTO;
import com.sahil.SplitwiseApp.mapper.DebtUsersMapper;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.repo.DebtUsersRepo;
import com.sahil.SplitwiseApp.validation.DebtUsersValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
public class DebtUsersService implements IDebtUsersService{

    @Autowired
    private DebtUsersRepo repo;

    @Autowired
    private DebtUsersMapper mapper;

    @Autowired
    private DebtUsersValidation debtUsersValidation;

    public DebtUsersDTO convertToDto(DebtUsers debtUser){
        return mapper.convertToDebtUsersDTO(debtUser);
    }

    public void addDebtUser(DebtUsers debtUser){
        debtUsersValidation.isValidDebtUser(debtUser);
        repo.save(debtUser);
    }

    public void updateDebtUser(DebtUsers debtUser){
        debtUsersValidation.isValidDebtUser(debtUser);
        repo.save(debtUser);
    }

    public List<DebtUsersDTO> getDebtUsersByExpenseId(int expenseId){
        debtUsersValidation.validateExpenseByExpenseId(expenseId);

        List<DebtUsers> debtUsersList = repo.getDebtUsersByExpenseId(expenseId);
        return debtUsersList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<DebtUsersDTO> getDebtsByUserId(int userId){
        debtUsersValidation.existsById(userId);

        List<DebtUsers> debtUsersList = repo.getDebtsByUserId(userId);
        return debtUsersList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public DebtUsers getUserByExpenseIdAndUserId(int expenseId, int userId){
        debtUsersValidation.validateDebtUserByUserIdAndExpenseId(userId,expenseId);
        return repo.getDebtUserByExpenseIdAndUserId(expenseId, userId);
    }

    public void deleteDebtUserByExpenseIdAndUserId(int expenseId,int userId){
        debtUsersValidation.validateDebtUserByUserIdAndExpenseId(userId,expenseId);

        DebtUsers debtUser = getUserByExpenseIdAndUserId(expenseId,userId);
        repo.delete(debtUser);
    }

    public void updateDebtUserByExpenseIdAndUserId(int expenseId,int userId,boolean newState, BigDecimal newAmount){
        debtUsersValidation.validateDebtUserByUserIdAndExpenseId(userId,expenseId);

        DebtUsers debtUser = getUserByExpenseIdAndUserId(expenseId,userId);
        debtUser.setDebtAmount(newAmount);
        debtUser.setSettled(newState);

        repo.save(debtUser);
    }
}
