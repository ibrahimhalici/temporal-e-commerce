package com.hlc.temporal_client;

import com.hlc.temporal_client.model.Order;
import com.hlc.temporal_client.model.OrderStatus;
import com.hlc.temporal_client.temporal.TemporalService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final TemporalService temporalService;

    public OrderController(TemporalService temporalService) {
        this.temporalService = temporalService;
    }

    @PostMapping
    public ResponseEntity<Void> createOrder(@RequestBody Order order) {
        temporalService.startOrder(order);
        return ResponseEntity.status(202).build();
    }

}