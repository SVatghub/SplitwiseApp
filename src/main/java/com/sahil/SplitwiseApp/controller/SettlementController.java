package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.DTO.DebtUsersDTO;
import com.sahil.SplitwiseApp.DTO.PaymentDTO;
import com.sahil.SplitwiseApp.DTO.SettlementDTO;
import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.model.Expenses;
import com.sahil.SplitwiseApp.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiConstants.BASE_URL)
public class SettlementController {

    @Autowired
    SettlementService service;

    @Autowired
    DebtUsersController debtUsersController;

    @Autowired
    ExpensesController expensesController;

    @Autowired
    UsersController usersController;

    @GetMapping("/users/{user1-Id}/settle/{user2-Id}")
    public SettlementDTO debtUserToLenderHistory(@PathVariable("user1-Id") int debtUserId, @PathVariable("user2-Id") int lenderId) {
        return service.debtUserToLenderHistory(debtUserId,lenderId);
    }

    @PutMapping("/users/{user1-Id}/settle/{user2-Id}")
    public void settleDebtsBetweenUsers(@PathVariable("user1-Id") int debtUserId, @PathVariable("user2-Id") int lenderId){
        service.settleDebtsBetweenUsers(debtUserId,lenderId);
    }

    @GetMapping("/users/{user-id}/to-pay")
    public List<PaymentDTO> allSettlementsToPay(@PathVariable("user-id") int userId) {
        List<DebtUsersDTO> allDebts = debtUsersController.getDebtsByUserId(userId);
        Map<Integer, BigDecimal> paymentMap = new HashMap<>();

        for (DebtUsersDTO debt : allDebts) {
            if (!debt.isSettled()) {
                int expenseId = debt.getExpenseId();
                Optional<Expenses> expenseOpt = expensesController.getExpenseByExpenseId(expenseId);
                if (expenseOpt.isPresent()) {
                    Expenses expense = expenseOpt.get();
                    int expenseMakerId = expense.getUserId();
                    if (expenseMakerId != userId) {
                        BigDecimal amount = debt.getDebtAmount();
                        paymentMap.put(expenseMakerId, paymentMap.getOrDefault(expenseMakerId, BigDecimal.ZERO).add(amount));

                        List<DebtUsersDTO> makerDebts = debtUsersController.getDebtsByUserId(expenseMakerId);
                        for (DebtUsersDTO makerDebt : makerDebts) {
                            if (!makerDebt.isSettled()) {
                                int makerDebtExpenseId = makerDebt.getExpenseId();
                                Optional<Expenses> makerDebtExpense = expensesController.getExpenseByExpenseId(makerDebtExpenseId);
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
                    String creditorName = usersController.getUserById(creditorId).getName();
                    return new PaymentDTO(creditorId, totalAmount, creditorName);
                })
                .collect(Collectors.toList());
    }
}
