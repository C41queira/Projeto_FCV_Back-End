package com.projetofcv.rosangelaestetica.resource;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projetofcv.rosangelaestetica.entity.Order;
import com.projetofcv.rosangelaestetica.entity.dto.OrderDTO;
import com.projetofcv.rosangelaestetica.resource.util.URL;
import com.projetofcv.rosangelaestetica.service.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> findAll() {
        List<Order> list = service.findAll();
        list.sort(Comparator.comparing(Order::getDate).thenComparing(Order::getTime));

        List<OrderDTO> listDto = new ArrayList<>();

        for (Order o : list) {
            listDto.add(changeDTO(o));
        }

        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findByOrder(@PathVariable int id) {
        Order u = service.findById(id);
        return ResponseEntity.ok().body(u);
    }

    @GetMapping(value = "/search_orders")
    public ResponseEntity<List<Order>> searchOrderes(
            @RequestParam(value = "date", defaultValue = "") String date,
            @RequestParam(value = "userName", defaultValue = "") String name) {

        LocalDate localDate;

        if (date.equals("")) {
            localDate = null;
        } else {
            localDate = URL.decodeDate(date);
        }

        List<Order> listOrders = service.searchOrders(localDate, name);
        return ResponseEntity.ok().body(listOrders);
    }

    @GetMapping(value = "/search_orders_user/{id}")
    public ResponseEntity<List<OrderDTO>> findOrdersByUserClient(@PathVariable int id) {
        List<Order> listOrders = service.findOrdersByUserClient(id);
        List<OrderDTO> listDto = new ArrayList<>();

        for (Order o : listOrders) {
            listDto.add(changeDTO(o));
        }

        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody Order obj) {
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Order> update(@PathVariable int id, @RequestBody Order obj) {
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    public OrderDTO changeDTO(Order order) {
        OrderDTO dto = new OrderDTO(order);
        return dto;
    }
}
