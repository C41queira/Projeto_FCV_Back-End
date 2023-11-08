package com.projetofcv.rosangelaestetica.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetofcv.rosangelaestetica.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{  

    @Query("SELECT o FROM Order o WHERE o.date = :date")
    public List<Order> findOrdersByDate(@Param("date") LocalDate date);

    @Query("SELECT i FROM Order i WHERE i.userClient.id = :userId")
    public List<Order> findOrdersByUserId(@Param("userId") Long id); 
}
