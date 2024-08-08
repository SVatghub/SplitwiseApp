package com.sahil.SplitwiseApp.mapper;

import com.sahil.SplitwiseApp.DTO.DebtUsersDTO;
import com.sahil.SplitwiseApp.model.DebtUsers;
import org.springframework.stereotype.Component;

@Component
public class DebtUsersMapper {

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
}
