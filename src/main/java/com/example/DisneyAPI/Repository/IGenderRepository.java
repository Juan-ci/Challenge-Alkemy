package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.GenderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IGenderRepository extends JpaRepository<GenderModel, Long >{
    
}
