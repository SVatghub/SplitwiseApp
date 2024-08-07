package com.sahil.SplitwiseApp.repo;

import com.sahil.SplitwiseApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {

    @Query("select u.id from Users u where u.name = ?1 and u.email = ?2")
    Integer getUserIdByNameAndEmail(String name, String email);
}
