package dev.anshul.EcomProductService.exception;

public class NoProductPresentException extends ProductPresentException{

    public NoProductPresentException(String message) {
        super(message);
    }

}
