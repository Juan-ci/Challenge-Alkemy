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
    
    private String userName;
    
    private String password;
    
    private String role;
    
    private String token;
    
    public UserModel convertToEntity() {
        return UserModel.builder()
                .userName(this.userName)
                .password(this.password)
                .role(this.role).build();
    }
}
