package com.example.DisneyAPI.dto;

import com.example.DisneyAPI.models.GenderModel;
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
public class GenderDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long idGender;
    
    private String nombre;
    
    private String imagen;
    
    public static GenderDto converToDto(GenderModel genderEntity) {
        return GenderDto.builder().idGender(genderEntity.getIdGender())
                                    .nombre(genderEntity.getNombre())
                                    .imagen(genderEntity.getImagen())
                                    .build();
    }
    
    public GenderModel converToEntity() {
        return GenderModel.builder().idGender(this.idGender)
                                    .nombre(this.nombre)
                                    .imagen(this.imagen)
                                    .build();
    }
}
