package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.CharacterModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICharacterRepository extends JpaRepository<CharacterModel, Long >{
    
}
