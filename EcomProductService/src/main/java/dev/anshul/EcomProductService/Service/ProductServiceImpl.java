package dev.anshul.EcomProductService.Service;

import dev.anshul.EcomProductService.dto.FakeStoreProductResponseDTO;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.ProductNotFoundException;
import dev.anshul.EcomProductService.repository.ProductRepository;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.property.access.internal.PropertyAccessStrategyCompositeUserTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//@Primary
@Service("productService")

public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(UUID productId) throws ProductNotFoundException {
        //why we are doing get --coz its optional, its like packet which contain might contain some data or may not be
        // long code
        /*Product savedProduct = productRepository.findById(productId).get();
        if (savedProduct == null){
            throw new ProductNotFoundException("Product not found for id:" + productId);
        }
        return savedProduct;*/
        //short code
        Product savedProduct = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id:" + productId)
        );
        return savedProduct;
    }

    @Override
    public Product createProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return savedProduct;

    }

    @Override
    public Product updateProduct(Product updatedProduct, UUID productID) {
        Product savedProduct = productRepository.findById(productID).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id:" + productID));
        //here we will update which are required, not creation timestamp and UUID
        savedProduct.setTitle(updatedProduct.getTitle());
        savedProduct.setCategory(updatedProduct.getCategory());
        savedProduct.setRating(updatedProduct.getRating());
        savedProduct.setImageURL(updatedProduct.getImageURL());
        savedProduct.setPrice(updatedProduct.getPrice());
        savedProduct.setDescription(updatedProduct.getDescription());
        savedProduct = productRepository.save(savedProduct);// save works as upsert,    which is update and insert
        //if wuse dirrectly .save method without fetching then sql get confused do i need to insert new entry/object or update it
        //this updated product is coming from the database or user side--user side, so db has no idea of this object. then how it will know
        // that this is the obbject where i need to update the timestamp 
        // student assked why not to use directly productRepository.save(updatedProduct) sir explained in notes why it will not work--if we do it will override, jpa queries and cardialty mapping
        return savedProduct;
    }

    @Override
    public boolean deleteProduct(UUID productID) {
        //should we worrry about if user try to edit the productID that does not exist--No, thats why we dont need any exception
        productRepository.deleteById(productID);
        return true;
    }

    @Override
    public Product getProduct(String productName) {
        return productRepository.findFirstProductByTitle(productName);
    }

    @Override
    public List<Product> getProducts(double minPrice, double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }
}
