package temporalworkers.workflow;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;
import temporalworkers.model.Order;

@WorkflowInterface
public interface StockWorkflow {
    @WorkflowMethod boolean doStock(Order order);

}
