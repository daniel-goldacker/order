package org.acme.controller;

import java.util.List;

import org.acme.dto.OrderDTO;
import org.acme.service.OrderService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/api/orders")
public class OrderController {

    @Inject
    private OrderService orderService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @POST
    @Transactional
    public Response saveOrder(OrderDTO orderDTO){
        try {
            orderService.saveOrder(orderDTO);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }
}
