package com.devsam.backend.ecommerce.infrastructure.adapter;

import com.devsam.backend.ecommerce.domain.model.Order;
import com.devsam.backend.ecommerce.domain.model.OrderState;
import com.devsam.backend.ecommerce.domain.port.IOrderRepository;
import com.devsam.backend.ecommerce.infrastructure.entity.OrderEntity;
import com.devsam.backend.ecommerce.infrastructure.entity.UserEntity;
import com.devsam.backend.ecommerce.infrastructure.mapper.IOrderMapper;
import org.springframework.stereotype.Repository;

@Repository
public class OrderCrudRepositoryImpl implements IOrderRepository {
    // Definimos variables para Mapper y Order Crud Repository
    private final IOrderMapper iOrderMapper;
    private final IOrderCrudRepository iOrderCrudRepository;

    // Creamos las variables con constructor
    public OrderCrudRepositoryImpl(IOrderMapper iOrderMapper, IOrderCrudRepository iOrderCrudRepository) {
        this.iOrderMapper = iOrderMapper;
        this.iOrderCrudRepository = iOrderCrudRepository;
    }

    @Override
    public Order save(Order order) {
        OrderEntity orderEntity = iOrderMapper.toOrderEntity(order);

        // Rellenamos las lineas de detalle de cada producto
        orderEntity.getOrderProducts().forEach(
                orderProductEntity -> orderProductEntity.setOrderEntity(orderEntity)
        );

        return iOrderMapper.toOrder(iOrderCrudRepository.save(orderEntity));
    }

    @Override
    public Order findById(Integer id) {
        // Se debe trata como Optional (no exista el resgistro)
        return iOrderMapper.toOrder(iOrderCrudRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Orden con id: " + id + " no encontrada")
        ));
    }

    @Override
    public Iterable<Order> findAll() {
        return iOrderMapper.toOrderList(iOrderCrudRepository.findAll());
    }

    @Override
    public Iterable<Order> findByUserId(Integer userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        return iOrderMapper.toOrderList(iOrderCrudRepository.findByUserEntity(userEntity));
    }

    @Override
    public void updateStateById(Integer id, String state) {
        if (state.equals(OrderState.CANCELLED)){
            iOrderCrudRepository.updateStateById(id, OrderState.CANCELLED);
        }else{
            iOrderCrudRepository.updateStateById(id, OrderState.CONFIRMED);
        }
    }
}
