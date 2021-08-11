package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.GenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.IGenderRepository;
import com.example.DisneyAPI.dto.GenderDto;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityNotFoundException;

@Service
public class GenderService {

    @Autowired
    IGenderRepository genderRepository;

    @Transactional
    public GenderDto saveGender(GenderDto gender) {
        GenderModel genderEntity = this.genderRepository.save(gender.converToEntity(gender));
        gender.setIdGender(genderEntity.getIdGender());
        return gender;
    }

    @Transactional(readOnly = true)
    public List<GenderDto> getGenders() {
        List<GenderModel> gendersBD = this.genderRepository.findAll();

        List<GenderDto> gendersDto = new ArrayList();

        for (GenderModel genderEntity : gendersBD) {
            gendersDto.add(GenderDto.converToDto(genderEntity));
        }

        return gendersDto;
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            this.genderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public GenderDto updateGender(Long id, GenderDto gender) {
        GenderModel genderBD;
        try {
            genderBD = genderRepository.getById(id);
            System.out.println(genderBD);

            System.out.println("paso por try Gender");
            genderBD = GenderModel.builder()
                    .idGender(id)
                    .nombre(gender.getNombre())
                    .imagen(gender.getImagen())
                    .build();

            genderBD = genderRepository.saveAndFlush(genderBD);

            System.out.println("PASE POR ACA");

        } catch (EntityNotFoundException e) {
            genderBD = genderRepository.saveAndFlush(gender.converToEntity(gender));
            System.out.println("PASE POR CATCH Gender");
        }

        return GenderDto.converToDto(genderBD);
    }
}
