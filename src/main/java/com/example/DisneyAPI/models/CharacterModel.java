package com.example.DisneyAPI.models;

import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "disney_character")
public class CharacterModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idCharacter;
    
    @Column(length = 2147483647)
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
    @JoinTable(name = "character_movie",
     joinColumns = @JoinColumn(name = "idCharacter"),
     inverseJoinColumns = @JoinColumn(name = "idMovie"))
    private List<MovieModel> peliculasAsociadas;
    
}
