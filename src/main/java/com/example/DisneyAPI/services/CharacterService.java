package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.CharacterModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.ICharacterRepository;
import com.example.DisneyAPI.dto.CharacterDto;
import java.util.List;

@Service
public class CharacterService {
    
    @Autowired
    ICharacterRepository characterRepository;
    
    @Transactional(readOnly = true)
    public List<CharacterDto> getCharacters(){
        List<CharacterModel> characterBD = characterRepository.findAll();
        
        List<CharacterDto> characterDto = new ArrayList();
        
        for (CharacterModel characterEnt : characterBD) {
            characterDto.add(CharacterDto.getImagenNombre(characterEnt));
        }
        /*
         *ENTENDER COMO USAR EXPRESION LAMBDA
         */
        return characterDto;
    }
    
    @Transactional
    public CharacterDto saveCharacter(CharacterDto character) {
        CharacterModel characterBD;
        characterBD = characterRepository.save(character.convertToEntity());
        character.setIdCharacter(characterBD.getIdCharacter());
        return character;
    }
    
    @Transactional
    public boolean deleteCharacter(Long id) {
        try {
            characterRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /*
     * CREAR METODO PARA BUSQUEDA CON FILTRADO
     * CriteriaQuery
     */
}
