package com.hlc.temporal_client.temporal;

import com.hlc.temporal_client.model.Order;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkflow {

    @WorkflowMethod
    void fulfill(Order payload);
}
