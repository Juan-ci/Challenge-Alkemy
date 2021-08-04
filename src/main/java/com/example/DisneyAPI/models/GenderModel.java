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
@Table(name = "gender")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GenderModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idGender;
    
    @Column
    private String nombre;
    
    @Column(length = 2147483647)
    private String imagen;
    
    @OneToMany(mappedBy = "genero", cascade = CascadeType.PERSIST)
    private List<MovieModel> peliculasAsociadas;
}
