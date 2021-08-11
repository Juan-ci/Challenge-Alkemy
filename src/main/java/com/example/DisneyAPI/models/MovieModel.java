package com.example.DisneyAPI.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "movie")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idMovie;
    
    @Column()
    private String imagen;
    
    @Column
    private String titulo;
    
    @Column
    @NotNull
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fechaCreacion;
    
    @Column
    private Long calificacion;
    
    @ManyToMany(mappedBy = "peliculasAsociadas", fetch = FetchType.LAZY)
    private List<CharacterModel> personajesAsociados;
    
    @JoinColumn(name = "fk_gender")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private GenderModel genero;
}
