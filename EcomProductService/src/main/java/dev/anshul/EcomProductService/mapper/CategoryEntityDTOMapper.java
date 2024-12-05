package dev.anshul.EcomProductService.mapper;

import dev.anshul.EcomProductService.dto.CategoryResponseDTO;
import dev.anshul.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.anshul.EcomProductService.dto.ProductResponseDTO;
import dev.anshul.EcomProductService.entity.Category;
import dev.anshul.EcomProductService.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryEntityDTOMapper {
//call productentity to product response dto mapper

    public static CategoryResponseDTO convertCategoryToCategoryResponseDTO(Category category){
        //call product entity to product response dto mapper as we are passing list of productresponsedto in category response
        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryName(category.getName());
        List<ProductResponseDTO> productResponseDTOs = new ArrayList<>();
        if(!(category.getProducts() == null || category.getProducts().isEmpty())) {
            for (Product product : category.getProducts()) {
                productResponseDTOs.add(ProductEntityDTOMapper.convertProductEntityToProductResponseDTO(product));
            }
        }
        categoryResponseDTO.setProducts(productResponseDTOs);
        return categoryResponseDTO;
    }

    public static Category convertCreateCategoryDTOToCategory(CreateCategoryRequestDTO createCategoryRequestDTO){
        Category category = new Category();
        category.setName(createCategoryRequestDTO.getCategoryName());
        return category;
    }
}
