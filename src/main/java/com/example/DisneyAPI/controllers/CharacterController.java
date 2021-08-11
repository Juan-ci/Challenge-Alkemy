package com.example.DisneyAPI.controllers;

import com.example.DisneyAPI.dto.CharacterDto;
import com.example.DisneyAPI.services.CharacterService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
public class CharacterController {
    
    @Autowired
    CharacterService characterService;
    
    @GetMapping()
    public List<CharacterDto> getCharacter(
    @RequestParam(value = "idcharacter",required = false) Optional<Long> idCharacter,
    @RequestParam(value = "name",required = false) Optional<String> nombre,
    @RequestParam(value = "edad",required = false) Optional<Long> edad
    ){
        Map< String, Object > filterBy = new HashMap<>();
        
        idCharacter.ifPresent( idcharacter -> filterBy.put("idcharacter", idcharacter));
        nombre.ifPresent( name -> filterBy.put("nombreCharacter", name));
        edad.ifPresent( age -> filterBy.put("age", age));
        //System.out.println(filterBy); Todos los valores en el map
        return characterService.getCharacters(filterBy);
    }
    
    @GetMapping(path = "/{id}")
    public CharacterDto getCharacterById(@PathVariable Long id){
        return characterService.getCharacter(id);
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
    
    @PutMapping(path = "/{id}")
    public CharacterDto updateCharacter(@PathVariable Long id, @RequestBody CharacterDto character){
        return this.characterService.updateCharacter(id, character);
    }
}
