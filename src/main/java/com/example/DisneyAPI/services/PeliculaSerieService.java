package com.example.DisneyAPI.services;

import com.example.DisneyAPI.dto.PeliculaSerieDto;
import com.example.DisneyAPI.model.PeliculaSerieModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeliculaSerieService {
    
    @Autowired
    PeliculaSerieDto peliculaSerieDto;
    
    public ArrayList<PeliculaSerieModel> obtenerPeliculas() {
        return (ArrayList<PeliculaSerieModel>) peliculaSerieDto.findAll();
    }
    
    public PeliculaSerieModel guardarPeliculaSerie(PeliculaSerieModel peliculaSerie) {
        return peliculaSerieDto.save(peliculaSerie);
    }
}
