package org.acme.service;

import java.util.ArrayList;
import java.util.List;

import org.acme.client.CustomerClient;
import org.acme.client.ProductClient;
import org.acme.dto.CustomerDTO;
import org.acme.dto.OrderDTO;
import org.acme.entity.OrderEntity;
import org.acme.repository.OrderRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class OrderService {

    @Inject
    private OrderRepository orderRepository;

    @Inject
    @RestClient
    private CustomerClient customerClient;

    @Inject
    @RestClient
    private ProductClient productClient;

    public List<OrderDTO> getAllOrders(){
        List<OrderDTO> orders = new ArrayList<>();
        
        orderRepository.findAll().stream().forEach(item ->{
            orders.add(mapOrderEntityToDTO(item));
        });

        return orders;
    }

    public void saveOrder(OrderDTO orderDTO){
        CustomerDTO customerDTO = customerClient.getCustomerById(orderDTO.getCustomerId());

        if (customerDTO.getName().equals(orderDTO.getCustomerName())
            && productClient.getProductById(orderDTO.getProductId()) != null){
            orderRepository.persist(mapOrderDTOToEntity(orderDTO));
        } else {
            throw new NotFoundException();
        }
    } 

    private OrderDTO mapOrderEntityToDTO(OrderEntity orderEntity){
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setCustomerId(orderEntity.getCustomerId());
        orderDTO.setCustomerName(orderEntity.getCustomerName());
        orderDTO.setOrderValue(orderEntity.getOrderValue());
        orderDTO.setProductId(orderEntity.getProductId());

        return orderDTO;
    }

  private OrderEntity mapOrderDTOToEntity(OrderDTO orderDTO){
        OrderEntity orderEntity = new OrderEntity();

        orderEntity.setCustomerId(orderDTO.getCustomerId());
        orderEntity.setCustomerName(orderDTO.getCustomerName());
        orderEntity.setOrderValue(orderDTO.getOrderValue());
        orderEntity.setProductId(orderDTO.getProductId());

        return orderEntity;
    }


}
