package com.example.DisneyAPI.model;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "peliculaserie")
public class PeliculaSerie {
    
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
    private List<Personaje> personajesAsociados;
    
}
