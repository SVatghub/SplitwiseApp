package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.PaymentDTO;
import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.SettlementDTO;
import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.service.settlements.ISettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.BASE_URL)
public class SettlementController {

    @Autowired
    private ISettlementService service;

    @GetMapping("/users/{debtUser-Id}/settle/{lender-Id}")
    public SettlementDTO debtUserToLenderHistory(@PathVariable("debtUser-Id") int debtUserId, @PathVariable("lender-Id") int lenderId) {
        return service.debtUserToLenderHistory(debtUserId,lenderId);
    }

    @PutMapping("/users/{debtUser-Id}/settle/{lender-Id}")
    public void settleDebtsBetweenUsers(@PathVariable("debtUser-Id") int debtUserId, @PathVariable("lender-Id") int lenderId){
        service.settleDebtsBetweenUsers(debtUserId,lenderId);
    }

    @GetMapping("/users/{debtUser-Id}/debts-user-to-user")
    public List<PaymentDTO> allSettlementsToPay(@PathVariable("debtUser-Id") int userId) {
        return service.allSettlementsToPay(userId);
    }
}
