package com.sahil.SplitwiseApp.service.debtUsers;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.DebtUsersDTO;
import com.sahil.SplitwiseApp.model.DebtUsers;
import java.math.BigDecimal;
import java.util.List;

public interface IDebtUsersService {
    DebtUsersDTO convertToDto(DebtUsers debtUser);
    void addDebtUser(DebtUsers debtUser);
    List<DebtUsersDTO> getDebtUsersByExpenseId(int expenseId);
    List<DebtUsersDTO> getDebtsByUserId(int userId);
    DebtUsers getUserByExpenseIdAndUserId(int expenseId, int userId);
    void deleteDebtUserByExpenseIdAndUserId(int expenseId,int userId);
    void updateDebtUserByExpenseIdAndUserId(int expenseId,int userId,boolean newState, BigDecimal newAmount);
    void updateDebtUser(DebtUsers debtUser);
}
