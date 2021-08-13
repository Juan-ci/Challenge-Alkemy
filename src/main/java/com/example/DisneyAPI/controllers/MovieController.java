package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.dto.MovieDto;
import com.example.DisneyAPI.services.MovieService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieController {
    
    @Autowired
    MovieService movieService;
    
    @GetMapping()
    public List<MovieDto> getMovies(
    @RequestParam(value = "name",required = false) Optional<String> nombre,
    @RequestParam(value = "genre",required = false) Optional<Long> idGenero,
    @RequestParam(value = "order",required = false) Optional<String> order
    ){
        Map< String, Object > filterBy = new HashMap<String, Object>();
        
        nombre.ifPresent( name -> filterBy.put("nombreMovie", name));
        idGenero.ifPresent( idgenero -> filterBy.put("idgenero", idgenero));
        order.ifPresent( orden -> filterBy.put("order", orden));
        //System.out.println(filterBy); Todos los valores en el map
        return movieService.getMovies(filterBy);
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
    
    @PutMapping(path = "/{id}")
    public MovieDto updateMovie(@PathVariable Long id, @RequestBody MovieDto character){
        System.out.println("Paso por put");
        return this.movieService.updateMovie(id, character);
    }
}
