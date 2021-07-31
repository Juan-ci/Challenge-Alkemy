package com.example.DisneyAPI.models;

import com.sun.istack.NotNull;
import javax.persistence.*;import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true)
    private Long idUser;
    
    @NotNull
    @Column
    private String userName;
    
    @NotNull
    @Column()
    private String password;
    
    @NotNull
    @Column()
    private String role;
}
