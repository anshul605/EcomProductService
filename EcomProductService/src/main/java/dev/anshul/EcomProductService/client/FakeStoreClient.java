package dev.anshul.EcomProductService.client;


import dev.anshul.EcomProductService.dto.FakeStoreCartResponseDTO;
import dev.anshul.EcomProductService.dto.FakeStoreProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Component

public class FakeStoreClient {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Value("${fakestore.api.base.url}")
    private String fakeStoreAPIBaseURL;
    @Value("${fakestore.api.product.path}")
    private String fakeStoreAPIProductPath;
    @Value("${fakestore.api.cart.for.user.path}")
    private String fakeStoreAPICartForUser;


    public List<FakeStoreProductResponseDTO> getAllProducts(){
        String fakeStoreGetAllProductURL = fakeStoreAPIBaseURL.concat(fakeStoreAPIProductPath);
        RestTemplate restTemplate = restTemplateBuilder.build();
// serilaization and deserialisation- deserilization converts json to object
        ResponseEntity<FakeStoreProductResponseDTO[]> productResponseList =
                restTemplate.getForEntity(fakeStoreGetAllProductURL, FakeStoreProductResponseDTO[].class);

        return List.of(productResponseList.getBody());
    }

    public FakeStoreProductResponseDTO getProductByID(int id){
        //url = https;//fakestoreapi.com/products/id
        String fakeStoreGetProductURL = fakeStoreAPIBaseURL.concat(fakeStoreAPIProductPath).concat("/"+ id);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.getForEntity(fakeStoreGetProductURL, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();

    }
    public List<FakeStoreCartResponseDTO> getCartByUserId(int userId){
        //if we give any user id, in cart product is coming to test the exeception write below line
        if (userId<1)
            return null;
        String fakeStoreGetCartForUser = fakeStoreAPIBaseURL.concat(fakeStoreAPICartForUser).concat(String.valueOf(userId));
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreCartResponseDTO> cartResponse =
                restTemplate.getForEntity(fakeStoreGetCartForUser, FakeStoreCartResponseDTO.class);
        return List.of(cartResponse.getBody());
    }



}
