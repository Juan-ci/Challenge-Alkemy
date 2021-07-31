package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.models.GenderModel;
import com.example.DisneyAPI.services.GenderService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genders")
public class GenderController {
    
    @Autowired
    GenderService genderService;
    
    @GetMapping()
    public ArrayList<GenderModel> obtenerGeneros(){
        return (ArrayList<GenderModel>)genderService.findAll();
    }
    
    @PostMapping()
    public GenderModel guardarPersonaje(@RequestBody GenderModel character){
        return this.genderService.saveGender(character);
    }
    
    @DeleteMapping("/{id}")
    public String borrarGenero(@PathVariable("id") Long id){
       boolean borrar = genderService.delete(id);
       
       if(borrar) {
           return "Se pudo borrar el género con id " + id;
       } else {
           return "No se pudo borrar el género con id " + id;
       }
    }
}
