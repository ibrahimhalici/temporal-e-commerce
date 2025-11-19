package com.hlc.temporal_client.temporal;

import com.hlc.temporal_client.model.Order;
import com.hlc.temporal_client.model.OrderStatus;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TemporalService {

    private final WorkflowClient temporalClient;

    public TemporalService(WorkflowClient temporalClient) {
        this.temporalClient = temporalClient;
    }

    private final String TASK_QUEQUE = "HLC_TRADING_ORDERS";

    public void startOrder(Order order) {
        // start the workflow
        OrderWorkflow workflow =
                temporalClient.newWorkflowStub(
                        OrderWorkflow.class,
                        WorkflowOptions.newBuilder()
                                .setWorkflowId(UUID.randomUUID().toString())
                                .setTaskQueue(TASK_QUEQUE)
                                .build());

        // start async
        WorkflowClient.start(workflow::fulfill, order);
        // this would do the same thing but sync.
//        workflow.submit(order);
    }
}
