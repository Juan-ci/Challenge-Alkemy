package com.example.DisneyAPI.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pelicula_serie")
public class PeliculaSerieModel {
    
    private static final Long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idpeliculaSerie;
    
    @Column
    private String imagen;
    
    @Column
    private String titulo;
    
    @Column
    private String fechaCreacion;
    
    @Column
    private int calificacion;
    
    @ManyToMany(mappedBy = "peliculasSeriesAsociadas", fetch = FetchType.LAZY)
    private List<PersonajeModel> personajesAsociados;
    
}
