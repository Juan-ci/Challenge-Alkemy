package com.example.DisneyAPI.dto;

import com.example.DisneyAPI.models.MovieModel;
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
    
    private Long calificacion;
    
    //private List<CharacterModel> personajesAsociados;
    
    public static MovieDto converToDto(MovieModel entity) {
        return MovieDto.builder()
                            .idMovie(entity.getIdMovie())
                            .imagen(entity.getImagen())
                            .titulo(entity.getTitulo())
                            .fechaCreacion(entity.getFechaCreacion())
                            .calificacion(entity.getCalificacion())
                            .build();
    }
    
    public MovieModel converToEntity(MovieDto movieDto) {
        return MovieModel.builder().imagen(this.imagen)
                                    .titulo(this.titulo)
                                    .fechaCreacion(this.fechaCreacion)
                                    .calificacion(this.calificacion)
                                    .build();
    }
}
