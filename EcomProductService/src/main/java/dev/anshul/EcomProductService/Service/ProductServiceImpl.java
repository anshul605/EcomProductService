package dev.anshul.EcomProductService.Service;

import dev.anshul.EcomProductService.dto.CreateProductRequestDTO;
import dev.anshul.EcomProductService.dto.ProductResponseDTO;
import dev.anshul.EcomProductService.entity.Category;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.CategoryNotFoundException;
import dev.anshul.EcomProductService.exception.ProductNotFoundException;
import dev.anshul.EcomProductService.mapper.ProductEntityDTOMapper;
import dev.anshul.EcomProductService.repository.CategoryRepoistory;
import dev.anshul.EcomProductService.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//@Primary
@Service("productService")

public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepoistory categoryRepoistory;

    @Override
    //Earlier we were returning entity itself for simplicity
    public List<ProductResponseDTO> getAllProducts() {
        List<Product> savedProducts = productRepository.findAll();
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : savedProducts){
            productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }

    @Override
    public ProductResponseDTO getProduct(UUID productId) throws ProductNotFoundException {
        //why we are doing get --coz its optional, its like packet which contain might contain some data or may not be
        // long code
        /*Product savedProduct = productRepository.findById(productId).get();
        if (savedProduct == null){
            throw new ProductNotFoundException("Product not found for id:" + productId);
        }
        return savedProduct;*/
        //short code
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id:" + productId)
        );
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO createProduct(CreateProductRequestDTO productRequestDTO) {
        Product product = ProductEntityDTOMapper.convertCreateProductRequestDTOToProduct(productRequestDTO);
        //we dont have a category object so need a category service layer
        // we create category service
        //here should we return category or category dto,if i call this service method and return dto and again
        //need to convert the dto to entity in product service layer--so much of redo and stuff
        // not a good idea--service layer can talk to repo layer--the code become messy --its very subjective
        // its ok for service to talk to some other repo...tts doable
        // returning an entity from service is not doable---design is very subjective
        Category savedCategory = categoryRepoistory.findById(productRequestDTO.getCategoryId()).orElseThrow(
                () -> new CategoryNotFoundException("Category not found for id:" + productRequestDTO.getCategoryId()));
        product.setCategory(savedCategory);
        //while creation we dont need eager loading and lazy loading
        product = productRepository.save(product);
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product);
    }

    @Override
    public ProductResponseDTO updateProduct(CreateProductRequestDTO createProductRequestDTO, UUID productID) {
        Product savedProduct = productRepository.findById(productID).orElseThrow(
                () -> new ProductNotFoundException("Product not found for id:" + productID));
        //here we will update which are required, not creation timestamp and UUID
        savedProduct.setTitle(createProductRequestDTO.getTitle());
        //this will not work same as above method savedProduct.setCategory(updatedProduct.getCategory());
        // cannot be updated as this is done by user savedProduct.setRating(createProductRequestDTO.getRating());
        savedProduct.setImageURL(createProductRequestDTO.getImageURL());
        savedProduct.setPrice(createProductRequestDTO.getPrice());
        savedProduct.setDescription(createProductRequestDTO.getDescription());

        //lets say category change is not allowed

        savedProduct = productRepository.save(savedProduct);// save works as upsert,    which is update and insert
        //if wuse dirrectly .save method without fetching then sql get confused do i need to insert new entry/object or update it
        //this updated product is coming from the database or user side--user side, so db has no idea of this object. then how it will know
        // that this is the obbject where i need to update the timestamp 
        // student assked why not to use directly productRepository.save(updatedProduct) sir explained in notes why it will not work--if we do it will override, jpa queries and cardialty mapping
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(savedProduct);
    }

    @Override
    public boolean deleteProduct(UUID productID) {
        //should we worrry about if user try to edit the productID that does not exist--No, thats why we dont need any exception
        productRepository.deleteById(productID);
        return true;
    }

    @Override
    public ProductResponseDTO getProduct(String productName) {
        return ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(
                productRepository.findFirstProductByTitle(productName));
    }

    @Override
    public List<ProductResponseDTO> getProducts(double minPrice, double maxPrice) {
        List<Product> ProductByPrice = productRepository.findByPriceBetween(minPrice, maxPrice);
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (Product product : ProductByPrice){
            productResponseDTOS.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
        }
        return productResponseDTOS;
    }
}
