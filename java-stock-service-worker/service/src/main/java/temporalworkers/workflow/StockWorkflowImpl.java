package temporalworkers.workflow;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import temporalworkers.activity.StockActivities;
import temporalworkers.model.Order;
import temporalworkers.model.OrderStatus;
import temporalworkers.model.Product;

import java.time.Duration;

public class StockWorkflowImpl implements StockWorkflow {

    private final RetryOptions defaultRetryOptions = RetryOptions.newBuilder()
            .setMaximumAttempts(4)
            .setMaximumInterval(Duration.ofSeconds(10L))
            .setDoNotRetry("temporalworkers.exception.CheckStockException")
            .build();

    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(2)) // Max execution time for single Activity
            .setScheduleToCloseTimeout(Duration.ofSeconds(5000)) // Entire duration from scheduling to completion including queue time
            .setRetryOptions(defaultRetryOptions)
            .build();

    // ActivityStubs enable calls to methods as if the Activity object is local but actually perform an RPC invocation
    private final StockActivities accountActivityStub = Workflow.newActivityStub(StockActivities.class, defaultActivityOptions);

    @Override
    public boolean doStock(Order order) {

        for (Product product : order.getProducts()) {
            boolean stockAvailable = accountActivityStub.checkStock(product.getId(), product.getQuantity());
            if (stockAvailable) {
                accountActivityStub.decreaseStock(product.getId(), product.getQuantity());
            } else {
                return false;
            }
        }

        return true;
    }
}
