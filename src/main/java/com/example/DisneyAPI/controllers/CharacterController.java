package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.dto.CharacterDto;
import com.example.DisneyAPI.models.CharacterModel;
import com.example.DisneyAPI.services.CharacterService;
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
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    CharacterService characterService;
    
    @GetMapping()
    public ArrayList<CharacterModel> getCharacter(){
        return characterService.getCharacters();
    }
    
    @PostMapping()
    public CharacterDto guardarPersonaje(@RequestBody CharacterDto character){
        return this.characterService.saveCharacter(character);
    }
    
    @DeleteMapping( path = "/{id}")
    public String borrarPersonajeById(@PathVariable("id") Long id) {
        boolean borrado = this.characterService.deleteCharacter(id);
        
        if(borrado){
            return "Se eliminó correctamente el personaje con id  " + id;
        } else {
            return "No pudo eliminar el personaje con "+ id;
        }
    }
}
