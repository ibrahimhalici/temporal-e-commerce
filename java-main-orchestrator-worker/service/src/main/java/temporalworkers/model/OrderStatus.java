package temporalworkers.model;

public enum OrderStatus {
    CREATED("created"),
    PROCESSING("processing"),
    PROCESSING_STOCK("p_stock"),
    PROCESSING_PAYMENT("p_payment"),
    PROCESSING_SHIPPING("p_shipping"),
    PROCESSING_AI("p_ai"),
    STOCK_PROCESSED("stock_comp"),
    PAYMENT_PROCESSED("payment_comp"),
    SHIPPING_PROCESSED("shipping_comp"),
    AI_PROCESSED("ai_comp"),
    COMPLETED("completed");

    public final String status;

    private OrderStatus(String status) {
        this.status = status;
    }
}
