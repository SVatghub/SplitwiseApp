package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.DTO.SettlementDTO;
import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.service.SettlementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(ApiConstants.BASE_URL)
public class SettlementController {

    @Autowired
    SettlementService service;

    @GetMapping("/users/{user1-Id}/settle/{user2-Id}")
    public SettlementDTO debtUserToLenderHistory(@PathVariable("user1-Id") int debtUserId, @PathVariable("user2-Id") int lenderId) {
        return service.debtUserToLenderHistory(debtUserId,lenderId);
    }

    @PutMapping("/users/{user1-Id}/settle/{user2-Id}")
    public void debtUserToLenderSettle(@PathVariable("user1-Id") int debtUserId, @PathVariable("user2-Id") int lenderId){
        service.debtUserToLenderSettle(debtUserId,lenderId);
    }
}