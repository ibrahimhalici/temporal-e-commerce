package temporalworkers.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface StockActivities {

    @ActivityMethod
    boolean checkStock(long productId, int quantity);

    @ActivityMethod
    void decreaseStock(long productId, int quantity);
}
