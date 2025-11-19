package temporalworkers.activity;

import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import temporalworkers.exception.CheckStockException;
import temporalworkers.exception.DecreaseStockException;

public class StockActivitiesImpl implements StockActivities {

    @Override
    public boolean checkStock(long productId, int quantity) throws CheckStockException {
        System.out.printf("checking if %d stock exists productId=%d \n", quantity, productId);
//        throw new CheckStockException("non-retryable");
        return true;
    }

    @Override
    public void decreaseStock(long productId, int quantity) throws DecreaseStockException {
        ActivityExecutionContext context = Activity.getExecutionContext();
        int currentAttempt = context.getInfo().getAttempt();
        System.out.printf("decreaseStock called for productId=%d, quantity=%d, attempt=%d\n", productId, quantity, currentAttempt);

        // Fail for the first two attempts (attempt 1 and 2)
        if (currentAttempt <= 2) {
            System.out.println("Simulating failure for attempt " + currentAttempt);
            throw new DecreaseStockException("Simulated failure on attempt " + currentAttempt);
        }

        // Succeed on the third attempt (attempt 3) and subsequent attempts
        System.out.printf("Stock for productId=%d decreased by %d. (Successful on attempt %d)\n", productId, quantity, currentAttempt);

    }
}
