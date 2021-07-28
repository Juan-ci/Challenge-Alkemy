package com.example.DisneyAPI.dto;

import com.example.DisneyAPI.model.PeliculaSerieModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeliculaSerieDto extends CrudRepository<PeliculaSerieModel, Long>{
    
}
