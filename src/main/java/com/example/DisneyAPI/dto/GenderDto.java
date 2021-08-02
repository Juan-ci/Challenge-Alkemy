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
public class GenderDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long idGender;
    
    private String nombre;
    
    private String imagen;
}
