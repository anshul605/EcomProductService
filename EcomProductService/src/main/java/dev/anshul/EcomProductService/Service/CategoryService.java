package dev.anshul.EcomProductService.Service;

import dev.anshul.EcomProductService.dto.CategoryResponseDTO;
import dev.anshul.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.anshul.EcomProductService.entity.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    CategoryResponseDTO getCategory (UUID categoryId);
    //here should we return category or category dto,if i call this service method and return dto and again
    //need to convert the dto to entity in product service layer--so much of redo and stuff
    // not a good idea--service layer can talk to repo layer

    List<CategoryResponseDTO> getAllCategories();
    CategoryResponseDTO createCategory (CreateCategoryRequestDTO createCategoryRequestDTO);
    CategoryResponseDTO updateCategory (CreateCategoryRequestDTO createCategoryRequestDTO, UUID categoryId);
    boolean deleteCategory (UUID categoryId);

}
