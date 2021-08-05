package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.UserModel;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository< UserModel, Long >{
    Optional<UserModel> findByuserName(String userName);
}
