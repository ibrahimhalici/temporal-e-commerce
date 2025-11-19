package temporalworkers.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import temporalworkers.model.Order;

@WorkflowInterface
public interface PaymentWorkflow {
    @WorkflowMethod boolean doPay(Order order);
}
