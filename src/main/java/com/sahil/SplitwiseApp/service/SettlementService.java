package com.sahil.SplitwiseApp.service;

import com.sahil.SplitwiseApp.DTO.DebtUsersDTO;
import com.sahil.SplitwiseApp.DTO.ExpensesDTO;
import com.sahil.SplitwiseApp.DTO.PaymentDTO;
import com.sahil.SplitwiseApp.DTO.SettlementDTO;
import com.sahil.SplitwiseApp.model.Expenses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SettlementService {

    @Autowired
    private ExpensesService expensesService;

    @Autowired
    private DebtUsersService debtUsersService;

    @Autowired
    private UsersService usersService;

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

    public List<PaymentDTO> allSettlementsToPay(int userId) {
        List<DebtUsersDTO> allDebts = debtUsersService.getDebtsByUserId(userId);
        Map<Integer, BigDecimal> paymentMap = new HashMap<>();

        for (DebtUsersDTO debt : allDebts) {
            if (!debt.isSettled()) {
                int expenseId = debt.getExpenseId();
                Optional<Expenses> expenseOpt = expensesService.getExpenseByExpenseId(expenseId);
                if (expenseOpt.isPresent()) {
                    Expenses expense = expenseOpt.get();
                    int expenseMakerId = expense.getUserId();
                    if (expenseMakerId != userId) {
                        BigDecimal amount = debt.getDebtAmount();
                        paymentMap.put(expenseMakerId, paymentMap.getOrDefault(expenseMakerId, BigDecimal.ZERO).add(amount));

                        List<DebtUsersDTO> makerDebts = debtUsersService.getDebtsByUserId(expenseMakerId);
                        for (DebtUsersDTO makerDebt : makerDebts) {
                            if (!makerDebt.isSettled()) {
                                int makerDebtExpenseId = makerDebt.getExpenseId();
                                Optional<Expenses> makerDebtExpense = expensesService.getExpenseByExpenseId(makerDebtExpenseId);
                                if(makerDebtExpense.isPresent() && makerDebtExpense.get().getUserId() == userId) {
                                    BigDecimal makerAmount = makerDebt.getDebtAmount();
                                    paymentMap.put(expenseMakerId, paymentMap.get(expenseMakerId).subtract(makerAmount));
                                }
                            }
                        }
                    }
                }
            }
        }

        return paymentMap.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(BigDecimal.ZERO) > 0)
                .map(entry -> {
                    int creditorId = entry.getKey();
                    BigDecimal totalAmount = entry.getValue();
                    String creditorName = usersService.getUserById(creditorId).getName();
                    return new PaymentDTO(creditorId, totalAmount, creditorName);
                })
                .collect(Collectors.toList());
    }

}
