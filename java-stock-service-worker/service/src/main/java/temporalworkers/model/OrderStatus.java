package temporalworkers.model;

public enum OrderStatus {
    CREATED("created"),
    PROCESSING("processing"),
    PROCESSING_STOCK("p_stock"),
    PROCESSING_PAYMENT("p_payment"),
    STOCK_PROCESSED("stock_comp"),
    PAYMENT_PROCESSED("payment_comp"),
    COMPLETED("completed");

    public final String status;

    private OrderStatus(String status) {
        this.status = status;
    }
}
