package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.CharacterModel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICharacterRepository extends CrudRepository< CharacterModel, Long >{
    
}
