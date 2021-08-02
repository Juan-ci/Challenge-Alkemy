package com.example.DisneyAPI.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CharacterDto implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private Long idCharacter;
    
    private String imagen;
    
    private String nombre;
    
    private int edad;
    
    private double peso;
    
    private String historia;
    
    //private List<MovieModel> peliculasAsociadas;
}
