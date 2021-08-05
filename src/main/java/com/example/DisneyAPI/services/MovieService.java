package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.MovieModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.IMovieRepository;
import com.example.DisneyAPI.dto.MovieDto;
import java.util.List;

@Service
public class MovieService {
    
    @Autowired
    IMovieRepository movieRepository;
    
    @Transactional(readOnly = true)
    public List<MovieDto> getMovie() {
        List<MovieDto> moviesDto = new ArrayList();
        List<MovieModel> movieBD = this.movieRepository.findAll();
        
        movieBD.forEach(movieModel -> {
            moviesDto.add(MovieDto.converToDto(movieModel));
        });
        /*
        for(MovieModel movieModel : movieBD) {
            moviesDto.add(MovieDto.converToEntity(movieModel));
        }
        */
        return moviesDto;
    }
    
    @Transactional
    public MovieDto saveMovie(MovieDto movieDto) {
        MovieModel movieBD = movieDto.converToEntity(movieDto);
        this.movieRepository.save(movieBD);
        
        movieDto.setIdMovie(movieDto.getIdMovie());
        
        movieDto.setIdMovie(movieBD.getIdMovie());
        
        return movieDto;
    }
    
    @Transactional
    public boolean deleteMovie(Long id) {
        try {
            this.movieRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
