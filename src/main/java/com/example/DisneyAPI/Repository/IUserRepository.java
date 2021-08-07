package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository< UserModel, Long >{
    UserModel findByUserName(String userName);
}
