package dev.anshul.EcomProductService.Controller;

import dev.anshul.EcomProductService.Service.ProductService;
import dev.anshul.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.InvalidInputException;
import dev.anshul.EcomProductService.exception.RandomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("productService")// this is representing inheritance
    private ProductService productService; // field injection , latter constructor and getter setter
//if we need multiple services
//    @Autowired
//    @Qualifier("fakeStoreProductService")
//    private ProductService productServiceImple;

    @GetMapping("/product")
    public ResponseEntity getAllProducts(){
        List<FakeStoreProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity getProductByID(@PathVariable("id") int id){

        if(id<=1){
            throw new InvalidInputException("Input is not correct");
        }
        FakeStoreProductResponseDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    @GetMapping("/productexception")
    public ResponseEntity getProductException(){
        throw new RandomException("Exception from Product");

    }

    @PostMapping("/product")
    public ResponseEntity createProduct(@RequestBody Product product){
        Product savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);

    }

}
