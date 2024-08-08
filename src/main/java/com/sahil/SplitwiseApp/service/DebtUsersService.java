package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.DTO.DebtUsersDTO;
import com.sahil.SplitwiseApp.mapper.DebtUsersMapper;
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

    @Autowired
    private DebtUsersMapper mapper;

    public DebtUsersDTO convertToDto(DebtUsers debtUser){
        return mapper.convertToDebtUsersDTO(debtUser);
    }

    public void addDebtUser(DebtUsers debtUser){
        repo.save(debtUser);
    }

    public List<DebtUsersDTO> getDebtUsersByExpenseId(int expenseId){
        List<DebtUsers> debtUsersList = repo.getDebtUsersByExpenseId(expenseId);
        return debtUsersList.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public List<DebtUsersDTO> getDebtsByUserId(int userId){
        List<DebtUsers> debtUsersList = repo.getDebtsByUserId(userId);
        return debtUsersList.stream().map(this::convertToDto).collect(Collectors.toList());
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
