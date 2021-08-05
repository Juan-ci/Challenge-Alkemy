package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository< MovieModel, Long >{
    
}
