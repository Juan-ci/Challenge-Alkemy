package com.example.DisneyAPI.dto;

import com.example.DisneyAPI.model.MovieModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDto extends CrudRepository<MovieModel, Long>{
    
}
