package com.projetofcv.rosangelaestetica.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.projetofcv.rosangelaestetica.entity.Order;
import com.projetofcv.rosangelaestetica.repository.OrderRepository;
import com.projetofcv.rosangelaestetica.service.exception.DataBaseException;
import com.projetofcv.rosangelaestetica.service.exception.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository; 

    public List<Order> findAll(){
        return orderRepository.findAll(); 
    }

    public Order findById(int id){
        Optional<Order> obj = orderRepository.findById(id); 
        return obj.orElseThrow(() -> new ResourceNotFoundException(id)); 
    }

    public List<Order> searchOrders(LocalDate date, String name){
        return orderRepository.searchOrders(date, name); 
    }

    public List<Order> findOrdersByUserClient(int id){
        return orderRepository.findOrdersByUserId(id); 
    }

    public Order insert(Order obj){
        return orderRepository.save(obj);
    }

    public void delete(int id){
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id); 
        } catch (DataIntegrityViolationException e){
            throw new DataBaseException(e.getMessage());
        }
        
    }

    public Order update(int id, Order Order){
        try {
            Order entity = orderRepository.getReferenceById(id); 
            updateData(entity, Order); 
            return orderRepository.save(entity); 
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id); 
        }
        
    }

    private void updateData(Order entity, Order order) {
        entity.setDate(order.getDate());
        entity.setOrderStatus(order.getOrderStatus());
    }
}
