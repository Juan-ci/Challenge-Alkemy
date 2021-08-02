package com.example.DisneyAPI.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "movie")
public class MovieModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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
    
    @JoinColumn(name = "fk_gender")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private GenderModel genero;
}
