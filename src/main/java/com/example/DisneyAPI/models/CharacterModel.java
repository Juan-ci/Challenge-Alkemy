package com.example.DisneyAPI.models;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "disney_character")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idCharacter;
    
    @Column(length = 2147483647)
    private String imagen;
    
    @Column
    private String nombre;
    
    @Column
    private Long edad;
    
    @Column
    private Double peso;
    
    @Column
    private String historia;
    
    @ManyToMany
    @JoinTable(name = "character_movie",
     joinColumns = @JoinColumn(name = "idCharacter"),
     inverseJoinColumns = @JoinColumn(name = "idMovie"))
    private List<MovieModel> peliculasAsociadas;
    
}
