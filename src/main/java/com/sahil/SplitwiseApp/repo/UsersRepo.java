package com.sahil.SplitwiseApp.repo;

import com.sahil.SplitwiseApp.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepo extends JpaRepository<Users,Integer> {

    @Query("select u.id from Users u where u.name = ?1 and u.email = ?2")
    Integer getUserIdByNameAndEmail(String name, String email);

    boolean existsByEmail(String email);

    Users findById(int id);

    @Query("select case when count(u) > 0 then true else false end from Users u where u.name = ?1 and u.email = ?2")
    boolean existsByNameAndEmail(String name, String email);
}
