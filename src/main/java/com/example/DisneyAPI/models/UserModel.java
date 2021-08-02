package com.example.DisneyAPI.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.istack.NotNull;
import java.io.Serializable;
import javax.persistence.*;import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserModel implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
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
    
    @Column
    private String token;
}
