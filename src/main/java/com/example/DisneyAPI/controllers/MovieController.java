package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.model.MovieModel;
import com.example.DisneyAPI.services.MovieService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculaserie")
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    @GetMapping()
    public ArrayList<MovieModel> obtenerPeliculaSeries(){
        return movieService.getMovie();
    }
    
    @PostMapping()
    public MovieModel guardarPeliculaSerie(@RequestBody MovieModel movie){
        return this.movieService.saveMovie(movie);
    }
    
    @DeleteMapping( path = "/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean borrado = this.movieService.deleteMovie(id);
        
        if(borrado){
            return "Se eliminó correctamente " + id;
        } else {
            return "No pudo eliminar el usuario "+ id;
        }
    }
    
    
}
