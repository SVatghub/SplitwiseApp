package com.sahil.SplitwiseApp.repo;

import com.sahil.SplitwiseApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {
}
