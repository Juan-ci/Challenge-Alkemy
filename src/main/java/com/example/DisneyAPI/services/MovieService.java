package com.example.DisneyAPI.services;

import com.example.DisneyAPI.model.MovieModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.DisneyAPI.dto.MovieDto;

@Service
public class MovieService {
    
    @Autowired
    MovieDto movieDto;
    
    public ArrayList<MovieModel> getMovie() {
        return (ArrayList<MovieModel>) movieDto.findAll();
    }
    
    public MovieModel saveMovie(MovieModel peliculaSerie) {
        return movieDto.save(peliculaSerie);
    }
    
    public boolean deleteMovie(Long id) {
        try {
            movieDto.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
