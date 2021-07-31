package com.example.DisneyAPI.services;

import com.example.DisneyAPI.Repository.IUserRepository;
import com.example.DisneyAPI.models.UserModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    
    @Autowired
    IUserRepository userRepository;
    
    @Transactional(readOnly = true)
    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }
    
    @Transactional
    public UserModel saveUser(UserModel user){
        return userRepository.saveAndFlush(user);
    }
    
    @Transactional
    public boolean deleteUser(Long id){
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
