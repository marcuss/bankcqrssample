package co.marcuss.cqrs.core.exceptions;

public class AggregateNotFoundException extends RuntimeException{
    public AggregateNotFoundException(String message) {
        super(message);
    }
}
