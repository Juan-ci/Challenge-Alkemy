package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.CharacterModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.ICharacterRepository;

@Service
public class CharacterService {
    
    @Autowired
    ICharacterRepository characterRepository;
    
    @Transactional(readOnly = true)
    public ArrayList<CharacterModel> getCharacters(){
        return (ArrayList<CharacterModel>) characterRepository.findAll();
    }
    
    @Transactional
    public CharacterModel saveCharacter(CharacterModel character) {
        return characterRepository.save(character);
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
