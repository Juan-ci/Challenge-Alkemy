package com.example.DisneyAPI.dto;

import com.example.DisneyAPI.models.UserModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long idUser;
    
    private String name;
    
    private String lastName;
    
    private String userName;
    
    private String mail;
    
    private String password;
    
    private String role;
    
    private String token;
    
    public UserModel convertToEntity() {
        return UserModel.builder()
                .name(this.name)
                .lastName(this.lastName)
                .userName(this.userName)
                .mail(this.mail)
                .password(this.password)
                .role(this.role).build();
    }
    
    public static UserDto convertToDto(UserModel userEntity) {
        return UserDto.builder()
                .idUser(userEntity.getIdUser())
                .name(userEntity.getName())
                .lastName(userEntity.getLastName())
                .userName(userEntity.getUserName())
                .mail(userEntity.getMail())
                .role(userEntity.getRole()).build();
    }
    
    public static UserModel setTokenEntity(String tokenDto) {
        return UserModel.builder()
                .token(tokenDto).build();
    }
}
