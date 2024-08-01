package com.sahil.SplitwiseApp.repo;

import com.sahil.SplitwiseApp.model.DebtUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtUsersRepo extends JpaRepository<DebtUsers,Integer> {
}
