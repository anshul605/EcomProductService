package dev.anshul.EcomProductService.Controller;

import dev.anshul.EcomProductService.client.FakeStoreClient;
import dev.anshul.EcomProductService.dto.FakeStoreCartResponseDTO;
import dev.anshul.EcomProductService.exception.CartNotFoundException;
import dev.anshul.EcomProductService.exception.RandomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CartController {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @GetMapping("/cart/{userId}")
    public ResponseEntity getCartByUserId(@PathVariable ("userId") int userId){
        List<FakeStoreCartResponseDTO> cartResponse = fakeStoreClient.getCartByUserId(userId);
        if (cartResponse == null){
            throw new CartNotFoundException("Cart not found for userId " + userId);
        }
        return ResponseEntity.ok(cartResponse);
    }

    @GetMapping("/cartexception")
    public ResponseEntity getCartException(){
        throw new RandomException("Exception from Cart");

    }
}
