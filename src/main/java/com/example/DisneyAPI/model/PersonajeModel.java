package com.example.DisneyAPI.model;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "personaje")
public class PersonajeModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersonaje;
    
    @Column
    private String imagen;
    
    @Column
    private String nombre;
    
    @Column
    private int edad;
    
    @Column
    private double peso;
    
    @Column
    private String historia;
    
    @ManyToMany
    @JoinTable(name = "personaje_peliculaserie",
     joinColumns = @JoinColumn(name = "idPersonaje"),
     inverseJoinColumns = @JoinColumn(name = "idpeliculaSerie"))
    private List<PeliculaSerieModel> peliculasSeriesAsociadas;
    
}
