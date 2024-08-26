package com.sahil.SplitwiseApp.mapper;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.DebtUsersDTO;
import com.sahil.SplitwiseApp.model.DebtUsers;
import org.springframework.stereotype.Component;

@Component
public class DebtUsersMapper {

    public DebtUsersDTO convertToDebtUsersDTO(DebtUsers debtUser){
        return DebtUsersDTO.builder()
                .id(debtUser.getId())
                .userId(debtUser.getUserId())
                .expenseId(debtUser.getExpense().getId())
                .createdAt(debtUser.getCreatedAt())
                .updatedAt(debtUser.getUpdatedAt())
                .debtAmount(debtUser.getDebtAmount())
                .isSettled(debtUser.isSettled())
                .build();
    }
}
