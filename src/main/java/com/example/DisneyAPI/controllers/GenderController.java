package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.dto.GenderDto;
import com.example.DisneyAPI.services.GenderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/genders")
public class GenderController {
    
    @Autowired
    GenderService genderService;
    
    @GetMapping()
    public List<GenderDto> obtenerGeneros(){
        return this.genderService.getGenders();
    }
    
    @PostMapping()
    public GenderDto guardarPersonaje(@RequestBody GenderDto character){
        return this.genderService.saveGender(character);
    }
    
    @DeleteMapping("/{id}")
    public String borrarGenero(@PathVariable("id") Long id){
       boolean borrar = this.genderService.delete(id);
       
       if(borrar) {
           return "Se pudo borrar el género con id " + id;
       } else {
           return "No se pudo borrar el género con id " + id;
       }
    }
    
    @PutMapping(path = "/{id}")
    public GenderDto updateGender(@PathVariable Long id, @RequestBody GenderDto gender){
        System.out.println("Paso por put");
        return this.genderService.updateGender(id, gender);
    }
}
