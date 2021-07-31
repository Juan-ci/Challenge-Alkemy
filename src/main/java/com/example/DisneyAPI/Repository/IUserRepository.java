package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository< UserModel, Long >{
    
}
