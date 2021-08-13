package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.CharacterModel;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.ICharacterRepository;
import com.example.DisneyAPI.dto.CharacterDto;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class CharacterService {

    @Autowired
    ICharacterRepository characterRepository;

    @PersistenceContext
    EntityManager entityManager;

    //Get characters with filters
    @Transactional(readOnly = true)
    public List<CharacterDto> getCharacters(Map< String, Object> filterBy) {
        List<CharacterModel> characterBD;
        characterBD = new ArrayList();

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<CharacterModel> character = criteriaQuery.from(CharacterModel.class);

        //Constructing list of parameters
        List<Predicate> predicates;
        predicates = new ArrayList();

        if (filterBy.containsKey("idcharacter")) {
            criteriaQuery.select(character)
                    .where(criteriaBuilder.equal(character.get("idCharacter"), filterBy.get("idcharacter")));

            characterBD.add((CharacterModel) entityManager.createQuery(criteriaQuery).getSingleResult());

            List<CharacterDto> characterDto;
            characterDto = new ArrayList();

            characterBD.forEach(characterEnt -> {
                characterDto.add(CharacterDto.getImagenNombre(characterEnt));
            });

            return characterDto;
        } else {
            if (filterBy.containsKey("nombreCharacter")) {
                predicates.add(criteriaBuilder.equal(character.get("nombre"), filterBy.get("nombreCharacter")));

            }

            if (filterBy.containsKey("age")) {
                predicates.add(criteriaBuilder.equal(character.get("edad"), filterBy.get("age")));
            }

            System.out.println("PREDICATES" + predicates);

            criteriaQuery.select(character)
                    .where(predicates.toArray(new Predicate[]{}));

            characterBD = entityManager.createQuery(criteriaQuery).getResultList();

            List<CharacterDto> characterDto = new ArrayList();

            characterBD.forEach(characterEnt -> {
                characterDto.add(CharacterDto.getImagenNombre(characterEnt));
            });

            return characterDto;
        }
    }

    //Character with details
    @Transactional(readOnly = true)
    public CharacterDto getCharacter(Long idCharacter) {
        CharacterModel characterBD;
        CharacterDto characterDto = null;

        try {
            System.out.println("ID: "+ idCharacter);
            characterBD = characterRepository.getById(idCharacter);

            //characterDto = CharacterDto.getCharacterDetails(characterBD);
            characterDto = CharacterDto.convertToDto(characterBD);
        } catch (EntityNotFoundException e) {
            System.out.println("Id no existente");
            e.getMessage();
        }
        return characterDto;
    }

    @Transactional
    public CharacterDto saveCharacter(CharacterDto character) {
        CharacterModel characterBD;
        characterBD = characterRepository.saveAndFlush(character.convertToEntity());
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

    @Transactional
    public CharacterDto updateCharacter(Long id, CharacterDto character) {
        CharacterModel characterBD;
        try {
            characterBD = characterRepository.getById(id);

            characterBD = CharacterModel.builder()
                    .idCharacter(id)
                    .imagen(character.getImagen())
                    .nombre(character.getNombre())
                    .edad(character.getEdad())
                    .peso(character.getPeso())
                    .historia(character.getHistoria())
                    .peliculasAsociadas(character.getPeliculasAsociadas())
                    .build();

            characterBD = characterRepository.saveAndFlush(characterBD);

            System.out.println("PASE POR ACA");

        } catch (EntityNotFoundException e) {
            characterBD = characterRepository.saveAndFlush(character.convertToEntity());
            System.out.println("PASE POR CATCH");
        }

        return CharacterDto.convertToDto(characterBD);
    }
}
