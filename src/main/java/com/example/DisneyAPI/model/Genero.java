package com.example.DisneyAPI.model;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "genero")
public class Genero {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long idGenero;
    
    @Column
    private String nombre;
    
    @Column
    private String imagen;
    
    /*
    @Column
    private List<String> peliculaSerieAsociada;
    */
}
