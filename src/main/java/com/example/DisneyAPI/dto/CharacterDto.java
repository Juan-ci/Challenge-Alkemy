package com.example.DisneyAPI.dto;

import com.example.DisneyAPI.models.CharacterModel;
import com.example.DisneyAPI.models.MovieModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
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

    private List<MovieModel> peliculasAsociadas;

    public CharacterModel convertToEntity() {
        return CharacterModel.builder()
                .imagen(this.imagen)
                .nombre(this.nombre)
                .edad(this.edad)
                .peso(this.peso)
                .historia(this.historia)
                .peliculasAsociadas(peliculasAsociadas).build();
    }

    public static CharacterDto convertToDto(CharacterModel entity) {
        return CharacterDto.builder()
                .idCharacter(entity.getIdCharacter())
                .imagen(entity.getImagen())
                .nombre(entity.getNombre())
                .edad(entity.getEdad())
                .peso(entity.getPeso())
                .historia(entity.getHistoria()).build();
    }

    /*
    public static CharacterDto getCharacterDetails(CharacterModel entity) {
        System.out.println("ESTOY EN DETAILS");
        CharacterDto characterDto = convertToDto(entity);
        System.out.println("CHARACTERDTO "+characterDto);
        characterDto.setPeliculasAsociadas(getPeliculas(entity.getPeliculasAsociadas()));
        //characterDto.setPeliculasAsociadas(entity.getPeliculasAsociadas());
        return characterDto;
    }

    private static List<MovieModel> getPeliculas(List<MovieModel> movies) {
        System.out.println("ESTOY EN GETPELICULAS");
        return movies.stream().map(movie -> MovieModel.builder()
                .idMovie(movie.getIdMovie())
                .titulo(movie.getTitulo())
                .imagen(movie.getImagen())
                .fechaCreacion(movie.getFechaCreacion())
                .build()).toList();
    }
    */

    public static CharacterDto getImagenNombre(CharacterModel entity) {
        return CharacterDto.builder()
                .imagen(entity.getImagen())
                .nombre(entity.getNombre()).build();
    }

    public static CharacterDto getCharacter(CharacterModel entity) {
        return CharacterDto.builder()
                .imagen(entity.getImagen())
                .nombre(entity.getNombre())
                .edad(entity.getEdad())
                .peso(entity.getPeso())
                .historia(entity.getHistoria())
                .build();
    }
}
