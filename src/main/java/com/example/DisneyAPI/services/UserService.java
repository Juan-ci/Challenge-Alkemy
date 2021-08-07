package com.example.DisneyAPI.services;

import com.example.DisneyAPI.Repository.IUserRepository;
import com.example.DisneyAPI.dto.Credential;
import com.example.DisneyAPI.dto.UserDto;
import com.example.DisneyAPI.models.UserModel;
import com.mailjet.client.errors.MailjetException;
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
import org.springframework.security.crypto.password.PasswordEncoder;

@Service("userDetailsService")
public class UserService {

    @Autowired
    IUserRepository userRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    MailSenderService mailService;
    
    @Transactional(readOnly = true)
    public List<UserDto> getUsers() {
        List<UserModel> usersBD = userRepository.findAll();
        
        List<UserDto> usersDto = new ArrayList();
        
        for (UserModel userEntity : usersBD) {
            usersDto.add(UserDto.convertToDto(userEntity));
        }
        System.out.println("Entidad convertida a Dto con éxito");
        return usersDto;
    }

    @Transactional
    public UserDto saveUser(UserDto user) throws MailjetException {
        UserModel userDevuelto;
        
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        userDevuelto = userRepository.saveAndFlush(user.convertToEntity());
        
        mailService.sendRegistrationEmail(user);
        /* -->Este código ya estaría funcionando, seteando el mail y apiKey en el .properties
            POR EL MOMENTO ESTÁ COMENTADO, YA QUE TENGO PROBLEMAS EN LA DOBLE AUTENTICACION
            QUE PIDE SENDGRID
        */

        user.setIdUser(userDevuelto.getIdUser());
        user.setMail(null);
        user.setPassword(null);

        return user;
    }

    public String logIn(Credential credentials) throws Exception {
        UserModel userBD = userRepository.findByUserName(credentials.getUserName());

        if (passwordEncoder.matches(credentials.getPassword(), userBD.getPassword())) {
            String token = this.getJWTToken(credentials.getUserName());
            System.out.println("¡¡¡TOKEN OBTENIDO!!!");
            userBD = UserDto.setTokenEntity(token);
            userRepository.saveAndFlush(userBD);
            return token;
        } else {
            throw new Exception("Check the credentials.-- ERROR AQUI!!");
        }
    }

    @Transactional
    public boolean deleteUser(Long id) {
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
                //.setExpiration(new Date(System.currentTimeMillis() + 600000)) <--Expiración comentada para facilitar pruebas
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return "Bearer " + token;
    }
}
