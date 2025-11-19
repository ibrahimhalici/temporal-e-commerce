package temporalworkers.exception;

public class DecreaseStockException extends RuntimeException {
    private String message;

    public DecreaseStockException(String message) {
        super(message);
    }
}
