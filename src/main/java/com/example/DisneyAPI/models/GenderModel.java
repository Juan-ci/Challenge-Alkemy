package com.example.DisneyAPI.models;

import javax.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gender")
public class GenderModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idGender;
    
    @Column
    private String nombre;
    
    @Column(length = 2147483647)
    private String imagen;
    
}
