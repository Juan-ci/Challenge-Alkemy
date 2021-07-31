package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.CharacterModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterRepository extends CrudRepository< CharacterModel, Long >{
    
}
