package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.dto.MovieDto;
import com.example.DisneyAPI.models.MovieModel;
import com.example.DisneyAPI.services.MovieService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    @GetMapping()
    public List<MovieDto> obtenerPeliculaSeries(){
        return this.movieService.getMovie();
    }
    
    @PostMapping()
    public MovieDto guardarPeliculaSerie(@RequestBody MovieDto movie){
        return this.movieService.saveMovie(movie);
    }
    
    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean borrado = this.movieService.deleteMovie(id);
        
        if(borrado){
            return "Se eliminó correctamente la película con id " + id;
        } else {
            return "No pudo eliminar la película con id "+ id;
        }
    }
}
