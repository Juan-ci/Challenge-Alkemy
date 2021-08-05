package com.example.DisneyAPI.dto;

import com.example.DisneyAPI.models.CharacterModel;
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
    
    private Long edad;
    
    private Double peso;
    
    private String historia;
    
    /*
     * private List<MovieModel> peliculasAsociadas;
     * AGREGAR CODIGO DE RELACIÓN ENTRE CLASES
     */
    
    public CharacterModel convertToEntity() {
        return CharacterModel.builder()
                .imagen(this.imagen)
                .nombre(this.nombre)
                .edad(this.edad)
                .peso(this.peso)
                .historia(this.historia).build();
    }
    
    public static CharacterDto getImagenNombre(CharacterModel entity) {
        return CharacterDto.builder()
                .imagen(entity.getImagen())
                .nombre(entity.getNombre()).build();
    }
}
