package com.sahil.SplitwiseApp.validation;

import com.sahil.SplitwiseApp.exceptions.DuplicateUserException;
import com.sahil.SplitwiseApp.exceptions.EmptyInputException;
import com.sahil.SplitwiseApp.exceptions.NullFieldException;
import com.sahil.SplitwiseApp.exceptions.ResourceNotFoundException;
import com.sahil.SplitwiseApp.repo.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersValidation {

    @Autowired
    private UsersRepo repo;

    private void notNullOrEmptyName(String name){
        if(name == null)
            throw new NullFieldException("Name cannot be null");
        else if(name.isEmpty())
            throw new EmptyInputException("Name cannot be empty");
    }

    private void notNullOrEmptyEmail(String email) {
        if (email == null)
            throw new NullFieldException("email cannot be null");
        else if (email.isEmpty())
            throw new EmptyInputException("email cannot be empty");
    }

    public void validateUser(String name,String email){
        notNullOrEmptyEmail(email);
        notNullOrEmptyName(name);
    }

    public void isDuplicateEmail(String email){
        if(repo.existsByEmail(email))
            throw new DuplicateUserException("User with this email already Exists");
    }

    public void existsById(int id){
        if(!repo.existsById(id))
            throw new ResourceNotFoundException("user not found with this id");
    }

    public void existsByNameAndEmail(String name,String email){
        if(!repo.existsByNameAndEmail(name,email)){
            throw new ResourceNotFoundException("user not found with this name and email");
        }
    }
}
