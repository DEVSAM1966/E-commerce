package com.devsam.backend.ecommerce.infrastructure.rest;

import com.devsam.backend.ecommerce.application.OrderService;
import com.devsam.backend.ecommerce.application.assembler.OrderAssembler;
import com.devsam.backend.ecommerce.domain.model.Order;
import com.devsam.backend.ecommerce.domain.model.OrderState;
import com.devsam.backend.ecommerce.infrastructure.rest.request.OrderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@Slf4j
public class OrderController {
    // Variable para el service de Order
    private final OrderService orderService;
    private final OrderAssembler orderAssembler;

    // Constructor de la variable servicio de Order
    public OrderController(OrderService orderService, OrderAssembler orderAssembler) {
        this.orderService = orderService;
        this.orderAssembler = orderAssembler;
    }

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
    /* public ResponseEntity<Order> save(@RequestBody OrderRequest request) {
        Order order = orderAssembler.toDomain(request); */

        // Verificamos el estado de order que viene por parámetros
        if (order.getOrderState().toString().equals(OrderState.CANCELLED.toString())){
            // Si es cancelado se vuelve a sobrescribir cancelado
            order.setOrderState(OrderState.CANCELLED);
        }else{
            // Diferente a cancelado, se sobrescribe como confirmado
            order.setOrderState(OrderState.CONFIRMED);
        }

        // Creamos el nuevo registro
        return ResponseEntity.ok(orderService.save(order));
    }

    @PostMapping("/update/state/order")
    public ResponseEntity updateStateById(@RequestParam Integer id, @RequestParam String state) {
        // Actualizamos el estado de la orden localizada por su id
        orderService.updateStateById(id, state);

        // Para que no de error al devolver ResponseEntity.ok() vacio
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Iterable<Order>> findAll() {
        // Devuelve todas las ordenes que exista
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("{variable}")
    public ResponseEntity<Order> findById(@PathVariable("variable") Integer id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @GetMapping("by-user/{id}")
    public ResponseEntity<Iterable<Order>> findByUserId(@PathVariable("id") Integer userId) {
        return ResponseEntity.ok(orderService.findByUserId(userId));
    }
}
