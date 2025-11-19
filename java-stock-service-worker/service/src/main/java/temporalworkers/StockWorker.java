package temporalworkers;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import temporalworkers.activity.StockActivitiesImpl;
import temporalworkers.workflow.StockWorkflowImpl;

public class StockWorker {
    private static final String TASK_QUEQUE = "HLC_TRADING_ORDERS_STOCK";

    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TASK_QUEQUE);

        worker.registerWorkflowImplementationTypes(StockWorkflowImpl.class);
        worker.registerActivitiesImplementations(new StockActivitiesImpl());

        System.out.println("Worker is running and actively polling the Task Queue.");
        System.out.println("To quit, use ^C to interrupt.");

        // Start all registered Workers. The Workers will start polling the Task Queue.
        factory.start();
    }
}
