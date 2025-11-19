package temporalworkers.exception;

public class CheckStockException extends RuntimeException{
    private String message;

    public CheckStockException(String message){
        super(message);
    }
}
