package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.models.UserModel;
import com.example.DisneyAPI.services.UserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserControlller {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/register")
    public UserModel guardarUsario(@RequestBody UserModel user){
        return this.userService.saveUser(user);
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
