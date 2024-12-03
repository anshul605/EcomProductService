package dev.anshul.EcomProductService.Service;

import dev.anshul.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.ProductNotFoundException;
import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    //this is used for fakestore product service impl
    //List<FakeStoreProductResponseDTO> getAllProducts();
    //FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException;
    List<Product> getAllProducts();
    //this is now UUID not int
    Product getProduct(UUID productId) throws ProductNotFoundException;
    Product createProduct(Product product);
    Product updateProduct(Product updatedProduct, UUID productID);
    boolean deleteProduct(UUID productID);
    Product getProduct(String productName);
    List<Product> getProducts(double minPrice, double maxPrice);
}
 