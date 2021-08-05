package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.GenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.IGenderRepository;
import com.example.DisneyAPI.dto.GenderDto;
import java.util.ArrayList;
import java.util.List;

@Service
public class GenderService {
    
    @Autowired
    IGenderRepository genderRepository;

    @Transactional
    public GenderDto saveGender(GenderDto gender) {
        GenderModel genderEntity = this.genderRepository.save(gender.converToEntity());
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
}
