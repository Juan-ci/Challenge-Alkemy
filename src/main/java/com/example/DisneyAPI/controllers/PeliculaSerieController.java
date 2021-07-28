package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.model.PeliculaSerieModel;
import com.example.DisneyAPI.services.PeliculaSerieService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peliculaserie")
public class PeliculaSerieController {
    
    @Autowired
    PeliculaSerieService peliculaSerieService;
    
    @GetMapping()
    public ArrayList<PeliculaSerieModel> obtenerPeliculaSeries(){
        return peliculaSerieService.obtenerPeliculas();
    }
    
    @PostMapping()
    public PeliculaSerieModel guardarPeliculaSerie(@RequestBody PeliculaSerieModel peliculaSerie){
        return this.peliculaSerieService.guardarPeliculaSerie(peliculaSerie);
    }
}
