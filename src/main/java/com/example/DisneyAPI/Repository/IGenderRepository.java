package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.GenderModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGenderRepository extends CrudRepository< GenderModel, Long >{
    
}
