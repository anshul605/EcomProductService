package dev.anshul.EcomProductService.Service;

import dev.anshul.EcomProductService.dto.CreateProductRequestDTO;
import dev.anshul.EcomProductService.dto.ProductResponseDTO;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    //this is used for fakestore product service impl
    //List<FakeStoreProductResponseDTO> getAllProducts();
    //FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException;
    List<ProductResponseDTO> getAllProducts();
    //this is now UUID not int
    ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException;
    ProductResponseDTO createProduct(CreateProductRequestDTO product);
    ProductResponseDTO updateProduct(CreateProductRequestDTO updatedProduct, UUID productID);
    boolean deleteProduct(UUID productID);
    ProductResponseDTO getProduct(String productName);
    List<ProductResponseDTO> getProducts(double minPrice, double maxPrice);
}
 