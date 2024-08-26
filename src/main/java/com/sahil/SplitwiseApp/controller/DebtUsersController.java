package com.sahil.SplitwiseApp.controller;

import com.sahil.SplitwiseApp.DTO.nonExceptionDTOs.DebtUsersDTO;
import com.sahil.SplitwiseApp.constants.ApiConstants;
import com.sahil.SplitwiseApp.model.DebtUsers;
import com.sahil.SplitwiseApp.service.debtUsers.IDebtUsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(ApiConstants.BASE_URL)
public class DebtUsersController {

    @Autowired
    private IDebtUsersService service;

    @PostMapping("/users/debt/{user-Id}")
    public void addDebtUser(DebtUsers debtUser){
        service.addDebtUser(debtUser);
    }

    @GetMapping("/users/debt/{user-Id}")
    public List<DebtUsersDTO> getDebtsByUserId(@PathVariable("user-Id") int userId){
        return service.getDebtsByUserId(userId);
    }

    @GetMapping("/debt-users/{expense-Id}")
    public List<DebtUsersDTO> getDebtUsersByExpenseId(@PathVariable("expense-Id") int expenseId){
        return service.getDebtUsersByExpenseId(expenseId);
    }

    @DeleteMapping("/users/{user-Id}/debt/{expense-Id}")
    public void deleteDebtUserByExpenseIdAndUserId(@PathVariable("expense-Id") int expenseId,@PathVariable("user-Id") int userId){
        service.deleteDebtUserByExpenseIdAndUserId(expenseId,userId);
    }

    @PutMapping("/users/{user-Id}/debt/{expense-Id}")
    public void updateDebtUserByExpenseIdAndUserId(@PathVariable("user-Id") int userId, @PathVariable("expense-Id") int expenseId, @RequestBody DebtUsers debtUser){
        service.updateDebtUserByExpenseIdAndUserId(expenseId,userId,debtUser.isSettled(),debtUser.getDebtAmount());
    }
}
