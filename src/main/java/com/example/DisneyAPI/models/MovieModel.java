package com.example.DisneyAPI.models;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movie")
public class MovieModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idMovie;
    
    @Column()
    private String imagen;
    
    @Column
    private String titulo;
    
    @Column
    private String fechaCreacion;
    
    @Column
    private int calificacion;
    
    @ManyToMany(mappedBy = "peliculasAsociadas", fetch = FetchType.LAZY)
    private List<CharacterModel> personajesAsociados;
    
}
