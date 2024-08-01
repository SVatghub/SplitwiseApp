package com.sahil.SplitwiseApp.repo;

import com.sahil.SplitwiseApp.model.Expenses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpensesRepo extends JpaRepository<Expenses,Integer> {
}
