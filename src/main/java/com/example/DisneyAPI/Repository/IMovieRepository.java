package com.example.DisneyAPI.Repository;

import com.example.DisneyAPI.models.MovieModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMovieRepository extends JpaRepository< MovieModel, Long >{
    
}
