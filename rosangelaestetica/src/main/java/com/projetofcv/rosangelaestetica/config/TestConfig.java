package com.projetofcv.rosangelaestetica.config;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projetofcv.rosangelaestetica.entity.Order;
import com.projetofcv.rosangelaestetica.entity.User;
import com.projetofcv.rosangelaestetica.entity.Work;
import com.projetofcv.rosangelaestetica.entity.enums.CategoryWork;
import com.projetofcv.rosangelaestetica.entity.enums.OrderStatus;
import com.projetofcv.rosangelaestetica.repository.OrderRepository;
import com.projetofcv.rosangelaestetica.repository.UserRepository;
import com.projetofcv.rosangelaestetica.repository.WorkRepository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository; 

    @Autowired
    private WorkRepository workRepository;

    @Autowired
    private OrderRepository orderRepository;


    @Override
    public void run(String... args) throws Exception {

        User uc1 = new User(1, "Erick Lima", "602.616.428-63", "aeV3taequ", "(71) 3060-5144", "ericklima@superrito.com", "32 anos");
        User uc2 = new User(2, "Carla Cavalcanti", "955.513.509-67", "gaeSie1fob", "(19) 3394-8012", "carlacavalcanti@jourrapide.com", "56 anos");


        Work w1 = new Work(null, "Massagem de mãos", CategoryWork.MASSAGE, "Massagem para as mãos para alivio dass dores", 120.00); 
        Work w2 = new Work(null, "Limpeza facial", CategoryWork.LIMPEZA, "limpefa para remoção de cravos e espinhas no rosto", 60.00); 
        Work w3 = new Work(null, "Massagem facial", CategoryWork.MASSAGE, "massagem para relaxamento dos musculos da face", 100.00);

        LocalDate date1 = LocalDate.of(2023, 9, 14);
        LocalDate date2 = LocalDate.of(2023, 10, 16);
        LocalTime time = LocalTime.of(10, 30, 0); 

        Order o1 = new Order(1, date2, time, OrderStatus.PAID, uc1, w1);
        Order o2 = new Order(2, date1, time, OrderStatus.WAITING_PAYMENT, uc2, w3);
        Order o3 = new Order(3, date2, time, OrderStatus.CANCELED, uc1, w2);


        userRepository.saveAll(Arrays.asList(uc1, uc2)); 

        workRepository.saveAll(Arrays.asList(w1, w2, w3));

        orderRepository.saveAll(Arrays.asList(o1, o2, o3)); 

    }

}
