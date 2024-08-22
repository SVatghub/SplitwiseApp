package com.sahil.SplitwiseApp.repo;

import com.sahil.SplitwiseApp.model.DebtUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DebtUsersRepo extends JpaRepository<DebtUsers,Integer> {
    List<DebtUsers> getDebtUsersByExpenseId(int expenseId);
    List<DebtUsers> getDebtsByUserId(int userId);
    DebtUsers getDebtUserByExpenseIdAndUserId(int expenseId,int userId);
}
