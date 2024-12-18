package dev.anshul.EcomProductService.Controller;

import dev.anshul.EcomProductService.Service.ProductService;
import dev.anshul.EcomProductService.dto.CreateProductRequestDTO;
import dev.anshul.EcomProductService.dto.ProductResponseDTO;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    @Qualifier("productService")// this is representing inheritance
    private ProductService productService; // field injection , latter constructor and getter setter
//if we need multiple services
//    @Autowired
//    @Qualifier("fakeStoreProductService")
//    private ProductService productServiceImple;

    @GetMapping
    //public ResponseEntity getAllProducts(){
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts(){
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponseDTO> getProductByID(@PathVariable("id") UUID id){

        //when id was int if(id<=1)
        if(id == null){
            throw new InvalidInputException("Input is not correct");
        }
        ProductResponseDTO product = productService.getProduct(id);
        return ResponseEntity.ok(product);
    }
    // used for demo of controller advice
    /*@GetMapping("/productexception")
    public ResponseEntity getProductException(){
        throw new RandomException("Exception from Product");

    }*/

    @PostMapping()
    public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody CreateProductRequestDTO product){
        ProductResponseDTO savedProduct = productService.createProduct(product);
        return ResponseEntity.ok(savedProduct);

    }
// Rest APi works at object level when update you have to send the whole object not the
    //parameter you updated, GraphQl solves this problem
    //whole json coming from FE is an object, so update happens at object level
    @PutMapping("/{id}")
    public ResponseEntity updateProduct(@PathVariable("id") UUID id, @RequestBody CreateProductRequestDTO productRequestDTO){
        ProductResponseDTO updatedProduct = productService.updateProduct(productRequestDTO, id);
        return ResponseEntity.ok(updatedProduct);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") UUID id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("name/{productName}")
    //when we hit "/{productName}" with this structure then its giving error
    //API structure is same for 2 apis, UUID or top api
    // in json UUID is string, so weather its is uuid or product name its string doesnot matter
    public ResponseEntity<ProductResponseDTO> getProductByProductName(@PathVariable("productName") String productName){
        return ResponseEntity.ok(productService.getProduct(productName));
    }


    @GetMapping("/{min}/{max}")
    public ResponseEntity<List<ProductResponseDTO>> getProductByPriceRange(@PathVariable("min") double minPrice,@PathVariable("max") double maxPrice){
        return ResponseEntity.ok(productService.getProducts(minPrice, maxPrice));
    }

}
