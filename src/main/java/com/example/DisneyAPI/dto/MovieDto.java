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
public class MovieDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long idMovie;
    
    private String imagen;
    
    private String titulo;
    
    private String fechaCreacion;
    
    //private int calificacion;
    
    //private List<CharacterModel> personajesAsociados;
}
