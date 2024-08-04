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

    public void debtUserToLenderSettle(int debtUserId, int lenderId){
        List<Optional<Expenses>> expensesByLender = expensesService.getExpenseByUserId(lenderId);

        for (Optional<Expenses> optionalExpense : expensesByLender) {
            optionalExpense.ifPresent(expense -> {
                expense.getDebtUsersList().stream()
                        .filter(debtUser -> debtUser.getUserId() == debtUserId && !debtUser.isSettled())
                        .forEach(debtUser -> {
                                debtUser.setSettled(true);
                                debtUsersService.updateDebtUser(debtUser);
                        });
            });
        }
    }
}
