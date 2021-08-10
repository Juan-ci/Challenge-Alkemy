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

    @Transactional(readOnly = true)
    public List<CharacterDto> getCharacters(Map< String, Object> filterBy) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<CharacterModel> character = criteriaQuery.from(CharacterModel.class);

        //Constructing list of parameters
        List<Predicate> predicates;
        predicates = new ArrayList();

        if (filterBy.containsKey("nombreCharacter")) {
            predicates.add(criteriaBuilder.equal(character.get("nombre"), filterBy.get("nombreCharacter")));

        }

        if (filterBy.containsKey("age")) {
            predicates.add(criteriaBuilder.equal(character.get("edad"), filterBy.get("age")));
        }

        System.out.println("PREDICATES" + predicates);

        criteriaQuery.select(character)
                .where(predicates.toArray(new Predicate[]{}));

        List<CharacterModel> characterBD2 = entityManager.createQuery(criteriaQuery).getResultList();

        System.out.println("Resultado " + characterBD2);

        List<CharacterModel> characterBD;
        characterBD = entityManager.createQuery(criteriaQuery).getResultList();

        List<CharacterDto> characterDto = new ArrayList();

        characterBD.forEach(characterEnt -> {
            characterDto.add(CharacterDto.getImagenNombre(characterEnt));
        });
        /*
         *ENTENDER COMO USAR EXPRESION LAMBDA
         */
        return characterDto;
    }

    @Transactional(readOnly = true)
    public CharacterDto getCharacter(Long idCharacter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery();
        Root<CharacterModel> character = criteriaQuery.from(CharacterModel.class);

        System.out.println("EL ID ES: " + idCharacter);
        criteriaQuery.select(character)
                .where(criteriaBuilder.equal(character.get("idCharacter"), idCharacter));

        CharacterModel characterBD;
        characterBD = (CharacterModel) entityManager.createQuery(criteriaQuery).getSingleResult();

        CharacterDto characterDto;
        characterDto = CharacterDto.getImagenNombre(characterBD);

        return characterDto;
    }

    /*
    @Transactional(readOnly = true)
    public List<CharacterDto> getCharacters(){
        List<CharacterModel> characterBD = characterRepository.findAll();
        
        List<CharacterDto> characterDto = new ArrayList();
        
        for (CharacterModel characterEnt : characterBD) {
            characterDto.add(CharacterDto.getImagenNombre(characterEnt));
        }
         *ENTENDER COMO USAR EXPRESION LAMBDA
        return characterDto;
    }
     */
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
