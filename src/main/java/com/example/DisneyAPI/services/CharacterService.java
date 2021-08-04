package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.CharacterModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.ICharacterRepository;
import com.example.DisneyAPI.dto.CharacterDto;

@Service
public class CharacterService {
    
    @Autowired
    ICharacterRepository characterRepository;
    
    @Transactional(readOnly = true)
    public ArrayList<CharacterModel> getCharacters(){
        /*
        *List<UserModel userBD = (ArrayList<CharacterModel>) characterRepository.findAll()
        
        List<UserDto> userDto;
        
        userBD.map(
            userDto = UserDto.builder()
                            .imagen(userBD.getImagen())
                            .nombre(userBD.getNombre()).build();
        )
        */
        return (ArrayList<CharacterModel>) characterRepository.findAll();
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
}
