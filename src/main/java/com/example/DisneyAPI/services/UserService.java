package com.example.DisneyAPI.services;

import com.example.DisneyAPI.Repository.IUserRepository;
import com.example.DisneyAPI.dto.UserDto;
import com.example.DisneyAPI.models.UserModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.authority.AuthorityUtils;

@Service
public class UserService {
    
    @Autowired
    IUserRepository userRepository;
    
    @Transactional(readOnly = true)
    public ArrayList<UserModel> getUsers(){
        return (ArrayList<UserModel>) userRepository.findAll();
    }
    
    @Transactional
    public UserDto saveUser(UserDto user){
        UserModel userDevuelto;
        
        UserModel userModel = UserModel.builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .role(user.getRole()).build();
        userDevuelto = userRepository.saveAndFlush(userModel);
        
        user.setIdUser(userDevuelto.getIdUser());
        user.setPassword(null);
        
        return user;
    }
    
    @Transactional
    public boolean deleteUser(Long id){
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		      List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("ROLE_USER");
		
		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}
}
