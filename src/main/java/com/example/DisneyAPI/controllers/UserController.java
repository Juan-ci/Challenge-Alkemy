package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.dto.UserDto;
import com.example.DisneyAPI.models.UserModel;
import com.example.DisneyAPI.services.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @Autowired
    UserDto userDto;
    /*
    @PostMapping("/register")
    public UserModel guardarUsario(@RequestBody UserModel user){
        return this.userService.saveUser(user);
    }
    */
    
    @PostMapping("/register")
    public UserDto guardarUsario(@RequestBody UserDto user){
        return this.userService.saveUser(user);
    }
        //return this.userService.saveUser(user);
    
    @PostMapping("/login")
    public UserModel login(@RequestBody UserModel user) {
		//ToDo: verificar si existe usuario, si existe, chequear password, despues generar token
		String token = userService.getJWTToken(user.getUserName());
		//UserModel user = new UserModel();
		//user.setUser(username);
		//user.setToken(token);		
		return user;
		
	}
    
    @GetMapping("/users")
    public ArrayList<UserModel> obtenerUsuarios(){
        //ToDo: crear userDTO para que devuelva todo menos el password
        return userService.getUsers();
    }
    
    @DeleteMapping(path = "/{id}")
    public String borrarUsuario(@PathVariable Long id){
        boolean borrado = userService.deleteUser(id);
        
        if(borrado){
            return "Se borró el usuario con id " + id;
        } else {
            return "No se pudo borrar el usuario con id " + id;
        }
    }
    
}
