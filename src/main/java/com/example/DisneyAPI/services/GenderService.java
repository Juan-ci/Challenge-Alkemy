package com.example.DisneyAPI.services;

import com.example.DisneyAPI.models.GenderModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.DisneyAPI.Repository.IGenderRepository;

@Service
public class GenderService {
    
    @Autowired
    IGenderRepository genderRepository;

    @Transactional
    public GenderModel saveGender(GenderModel gender) {
        return genderRepository.save(gender);
    }

    @Transactional(readOnly = true)
    public Iterable<GenderModel> findAll() {
        return genderRepository.findAll();
    }

    @Transactional
    public boolean delete(Long id) {
        try {
            genderRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
