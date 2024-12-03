package dev.anshul.EcomProductService.mapper;

import dev.anshul.EcomProductService.dto.CreateProductRequestDTO;
import dev.anshul.EcomProductService.dto.ProductResponseDTO;
import dev.anshul.EcomProductService.entity.Product;

public class ProductEntityDTOMapper {

    public static ProductResponseDTO convertProductEntityToProductResponseDTO(Product product){
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        //responseDTO.setTitle(product.getId());--why this will give error as this could be extend from base model
        responseDTO.setTitle(product.getTitle());
        //responseDTO.setCategory(product.getCategory());---when we set category as Category object in product entity this gives error
        responseDTO.setRating(product.getRating());
        responseDTO.setPrice(product.getPrice());
        responseDTO.setImageURL(product.getImageURL());
        responseDTO.setDescription(product.getDescription());
        responseDTO.setCategory(product.getCategory().getName());
        return responseDTO;
    }

    public static Product convertCreateProductRequestDTOToProduct (CreateProductRequestDTO productRequestDTO){
        Product product = new Product();
        product.setTitle(productRequestDTO.getTitle());
        product.setPrice(productRequestDTO.getPrice());
        product.setImageURL(productRequestDTO.getImageURL());
        product.setDescription(productRequestDTO.getDescription());
        return product;
        //here we are getting UUID but in product entity we are storing cateogry object so need to get cateory object
    }
}
