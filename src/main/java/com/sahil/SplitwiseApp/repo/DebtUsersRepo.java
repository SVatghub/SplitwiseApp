package com.sahil.SplitwiseApp.repo;

import com.sahil.SplitwiseApp.model.DebtUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebtUsersRepo extends JpaRepository<DebtUsers,Integer> {
    List<DebtUsers> getDebtUsersByExpenseId(int expenseId);
    List<DebtUsers> getDebtUsersByUserId(int userId);
    Optional<DebtUsers> getDebtUserByExpenseIdAndUserId(int expenseId,int userId);
}
