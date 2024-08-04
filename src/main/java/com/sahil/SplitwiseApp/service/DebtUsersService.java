package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.DTO.DebtUsersDTO;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.repo.DebtUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.math.BigDecimal;

@Service
public class DebtUsersService {

    @Autowired
    private DebtUsersRepo repo;

    public DebtUsersDTO convertToDebtUsersDTO(DebtUsers debtUser){
        DebtUsersDTO debtUsersDTO = new DebtUsersDTO();
        debtUsersDTO.setId(debtUser.getId());
        debtUsersDTO.setUserId(debtUser.getUserId());
        debtUsersDTO.setExpenseId(debtUser.getExpense().getId());
        debtUsersDTO.setCreatedAt(debtUser.getCreatedAt());
        debtUsersDTO.setUpdatedAt(debtUser.getUpdatedAt());
        debtUsersDTO.setSettled(debtUser.isSettled());
        debtUsersDTO.setDebtAmount(debtUser.getDebtAmount());
        return debtUsersDTO;
    }

    public void addDebtUser(DebtUsers debtUser){
        repo.save(debtUser);
    }

    public List<DebtUsersDTO> getDebtUsersByExpenseId(int expenseId){
        List<DebtUsers> debtUsersList = repo.getDebtUsersByExpenseId(expenseId);
        return debtUsersList.stream().map(this::convertToDebtUsersDTO).collect(Collectors.toList());
    }

    public List<DebtUsersDTO> getDebtUsersByUserId(int userId){
        List<DebtUsers> debtUsersList = repo.getDebtUsersByUserId(userId);
        return debtUsersList.stream().map(this::convertToDebtUsersDTO).collect(Collectors.toList());
    }

    public Optional<DebtUsers> getUserByExpenseIdAndUserId(int expenseId, int userId){
        return repo.getDebtUserByExpenseIdAndUserId(expenseId, userId);
    }

    public void deleteDebtUserByExpenseIdAndUserId(int expenseId,int userId){
        Optional<DebtUsers> optionalDebtUser = getUserByExpenseIdAndUserId(expenseId,userId);
        optionalDebtUser.ifPresent(debtUsers -> repo.delete(debtUsers));
    }

    public void updateDebtUserByExpenseIdAndUserId(int expenseId,int userId,boolean newState, BigDecimal newAmount){
        Optional<DebtUsers> optionalDebtUser = getUserByExpenseIdAndUserId(expenseId,userId);
        if(optionalDebtUser.isPresent()){
            DebtUsers existingDebtUser = optionalDebtUser.get();
            existingDebtUser.setDebtAmount(newAmount);
            existingDebtUser.setSettled(newState);
            repo.save(existingDebtUser);
        }
    }

    public void updateDebtUser(DebtUsers debtUser){
        repo.save(debtUser);
    }
}
