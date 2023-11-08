package com.projetofcv.rosangelaestetica.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetofcv.rosangelaestetica.entity.Work;
import com.projetofcv.rosangelaestetica.repository.WorkRepository;
import com.projetofcv.rosangelaestetica.service.exception.DataBaseException;
import com.projetofcv.rosangelaestetica.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class WorkService {
    
    @Autowired
    private WorkRepository workRepository; 

    public List<Work> findAll(){
        return workRepository.findAll(); 
    }

    public Work findById(Long id){
        Optional<Work> obj = workRepository.findById(id); 
        return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
    }

    public Work insert(Work obj){
        return workRepository.save(obj);
    }

    public void delete(Long id){
        try {
            workRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id); 
        } catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
        
    }

    public Work update(Long id, Work work){
        try {
            Work entity = workRepository.getReferenceById(id); 
            updateData(entity, work); 
            return workRepository.save(entity); 
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id); 
        }
        
    }

    private void updateData(Work entity, Work work) {
       entity.setName(work.getName());
       entity.setCategoryWork(work.getCategoryWork());
       entity.setInfo(work.getInfo());
       entity.setPrice(work.getPrice());
    }
}

