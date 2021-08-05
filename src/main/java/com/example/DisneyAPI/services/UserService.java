package com.example.DisneyAPI.services;

import com.example.DisneyAPI.Repository.IUserRepository;
import com.example.DisneyAPI.dto.Credential;
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
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
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
    public UserDto saveUser(UserDto user) {
        UserModel userDevuelto;
        
        /*
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Revisar chequeo de pass encriptado con el pass ingresado por el login
        */
        userDevuelto = userRepository.saveAndFlush(user.convertToEntity());
        
        /*
        mailService.sendRegistrationEmail(user); -->Este código ya estaría funcionando, seteando el mail y apiKey en el .properties
        POR EL MOMENTO ESTÁ COMENTADO, YA QUE TENGO PROBLEMAS EN LA DOBLE AUTENTICACION
        QUE PIDE SENDGRID
        */

        user.setIdUser(userDevuelto.getIdUser());
        user.setMail(null);
        user.setPassword(null);

        return user;
    }

    public String logIn(Credential credentials) throws Exception {

        UserModel userBD = userRepository.findByuserName(credentials.getUserName()).orElseThrow();

        if (credentials.getPassword().equals(userBD.getPassword())) {
            
            //Con el encoderPass deshabilitado, sólo comparo texto plano ingresado con texto plano en BD
            String token = this.getJWTToken(credentials.getUserName());
            System.out.println("¡¡¡TOKEN OBTENIDO!!!");
            userBD = UserDto.setTokenEntity(token);
            userRepository.saveAndFlush(userBD);
            return token;
        } else {
            return "ERROR AQUI";
            
        }
            /*
            String token = this.getJWTToken(credentials.getUserName());
            System.out.println("¡¡¡TOKEN OBTENIDO!!!");
            userBD.setToken(token);
            //userBD = UserDto.setTokenEntity(token);
            userRepository.saveAndFlush(userBD);
            return token;
        } else {
            throw new Exception("Check the credentials.");
        }
*/
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

        return "Disney " + token;
    }
}
