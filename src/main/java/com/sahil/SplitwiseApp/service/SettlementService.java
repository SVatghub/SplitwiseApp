package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.DTO.ExpensesDTO;
import com.sahil.SplitwiseApp.DTO.SettlementDTO;
import com.sahil.SplitwiseApp.model.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SettlementService {

    @Autowired
    private ExpensesService expensesService;

    @Autowired
    private DebtUsersService debtUsersService;

    public SettlementDTO debtUserToLenderHistory(int debtUserId, int lenderId) {
        List<Optional<Expenses>> expensesByLender = expensesService.getExpenseByUserId(lenderId);

        SettlementDTO settlementDTO = new SettlementDTO();
        settlementDTO.setToId(lenderId);
        settlementDTO.setFromId(debtUserId);

        List<ExpensesDTO> expensesDTOS = new ArrayList<>();

        for (Optional<Expenses> optionalExpense : expensesByLender) {
            optionalExpense.ifPresent(expense -> {
                expense.getDebtUsersList().stream()
                        .filter(debtUser -> debtUser.getUserId() == debtUserId)
                        .forEach(debtUser -> {
                            ExpensesDTO expenseDTO = new ExpensesDTO(
                                    debtUser.getId(),
                                    expense.getTitle(),
                                    debtUser.getDebtAmount(),
                                    debtUser.getCreatedAt(),
                                    debtUser.getUpdatedAt()
                            );
                            expensesDTOS.add(expenseDTO);
                        });
            });
        }
        settlementDTO.setExpensesDTOS(expensesDTOS);
        return settlementDTO;
    }

    public void settleDebtsBetweenUsers(int debtorId, int lenderId) {
        settleDebtsForUser(debtorId, lenderId);
        settleDebtsForUser(lenderId, debtorId);
    }

    private void settleDebtsForUser(int userId, int otherUserId) {
        List<Optional<Expenses>> expensesByUser = expensesService.getExpenseByUserId(userId);

        for (Optional<Expenses> optionalExpense : expensesByUser) {
            optionalExpense.ifPresent(expense -> {
                expense.getDebtUsersList().stream()
                        .filter(debtUser -> debtUser.getUserId() == otherUserId && !debtUser.isSettled())
                        .forEach(debtUser -> {
                            debtUser.setSettled(true);
                            debtUsersService.updateDebtUser(debtUser);
                        });
            });
        }
    }
}
