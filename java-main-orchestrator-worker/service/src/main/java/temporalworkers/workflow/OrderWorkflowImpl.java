package temporalworkers.workflow;


import io.temporal.workflow.ChildWorkflowOptions;
import io.temporal.workflow.Workflow;
import temporalworkers.model.Order;

public class OrderWorkflowImpl implements OrderWorkflow {
    private StockWorkflow stockWorkflow = Workflow.newChildWorkflowStub(StockWorkflow.class,
            ChildWorkflowOptions.newBuilder()
                    .setWorkflowId("stockChildWorkflow")
                    .setTaskQueue("HLC_TRADING_ORDERS_STOCK").build()
    );

    private PaymentWorkflow paymentWorkflow = Workflow.newChildWorkflowStub(PaymentWorkflow.class,
            ChildWorkflowOptions.newBuilder()
                    .setWorkflowId("paymentChildWorkflow")
                    .setTaskQueue("HLC_TRADING_ORDERS_PAYMENT").build());

    @Override
    public void fulfill(Order payload) {

        boolean stockRes = stockWorkflow.doStock(payload);
        if (stockRes) {
            boolean paymentRes = paymentWorkflow.doPay(payload);
            if (paymentRes) {
                System.out.printf("order workflow completed for orderId= %d", payload.getId());
            } else {
                System.out.printf("stock service failed with result = %b\n", paymentRes);
            }
        } else {
            System.out.printf("stock service failed with result = %b\n", stockRes);
        }
    }
}
