package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.DTO.DebtUsersDTO;
import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.service.DebtUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.BASE_URL)
public class DebtUsersController {

    @Autowired
    private DebtUsersService service;

    @PostMapping("/users/debt/{userId}")
    public void addDebtUser(DebtUsers debtUser){
        service.addDebtUser(debtUser);
    }

    @GetMapping("users/debt/{userId}")
    public List<DebtUsersDTO> getDebtUsersByUserId(@PathVariable int userId){
        return service.getDebtUsersByUserId(userId);
    }

    @GetMapping("debt-users/{expenseId}")
    public List<DebtUsersDTO> getDebtUsersByExpenseId(@PathVariable int expenseId){
        return service.getDebtUsersByExpenseId(expenseId);
    }

    @DeleteMapping("users/{userId}/debt/{expenseId}")
    public void deleteDebtUserByExpenseIdAndUserId(@PathVariable int expenseId,@PathVariable int userId){
        service.deleteDebtUserByExpenseIdAndUserId(expenseId,userId);
    }

    @PutMapping("users/{userId}/debt/{expenseId}")
    public void updateDebtUserByExpenseIdAndUserId(@PathVariable int userId, @PathVariable int expenseId, @RequestBody DebtUsers debtUser){
        service.updateDebtUserByExpenseIdAndUserId(expenseId,userId,debtUser.isSettled(),debtUser.getDebtAmount());
    }
}
