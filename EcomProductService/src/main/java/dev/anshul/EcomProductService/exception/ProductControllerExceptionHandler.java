package dev.anshul.EcomProductService.exception;

import dev.anshul.EcomProductService.Controller.ProductController;
import dev.anshul.EcomProductService.dto.ExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackageClasses = ProductController.class)
public class ProductControllerExceptionHandler {

    @ExceptionHandler({ProductNotFoundException.class, NoProductPresentException.class})
    public ResponseEntity handleProductNotFoundException(ProductPresentException pe){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                pe.getMessage(),404);
// how pe.getMessage is taking out the message need to check?????
///???????
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
    // dont do runtime exception as this is not a good practice anyway it will not handle all exception
    // //as we have mentioned which error to handle
    // if we replace productpresenet exception with runtime
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity handleNoProductFoundException(InvalidInputException pe){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                pe.getMessage(),400);
//instead of writing specific exception.class we can write runtime.class-
        //yes for every error what if we want to throw specific http request or comment
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CartNotFoundException.class)
    public ResponseEntity cartNotFoundException(CartNotFoundException pe){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                pe.getMessage(),404);
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(RandomException.class)
    public ResponseEntity handleProductRandomException(RandomException pe){
        ExceptionResponseDTO exceptionResponseDTO = new ExceptionResponseDTO(
                pe.getMessage(),404);
        return new ResponseEntity<>(exceptionResponseDTO, HttpStatus.NOT_FOUND);
    }
}
