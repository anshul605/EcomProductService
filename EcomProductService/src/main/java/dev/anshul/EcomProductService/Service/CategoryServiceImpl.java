package dev.anshul.EcomProductService.Service;

import dev.anshul.EcomProductService.client.FakeStoreClient;
import dev.anshul.EcomProductService.dto.CategoryResponseDTO;
import dev.anshul.EcomProductService.dto.CreateCategoryRequestDTO;
import dev.anshul.EcomProductService.dto.fakeSoreDtos.FakeStoreProductResponseDTO;
import dev.anshul.EcomProductService.entity.Category;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.CategoryNotFoundException;
import dev.anshul.EcomProductService.mapper.CategoryEntityDTOMapper;
import dev.anshul.EcomProductService.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public CategoryResponseDTO getCategory(UUID categoryId) {
        Category category =  categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryResponseDTO> categoryResponseDTOs = new ArrayList<>();
        for(Category c : categories){
            categoryResponseDTOs.add(CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(c));
        }
        return categoryResponseDTOs;
    }

    @Override
    public CategoryResponseDTO createCategory(CreateCategoryRequestDTO categoryRequestDTO) {
        Category category = CategoryEntityDTOMapper.convertCreateCategoryDTOToCategory(categoryRequestDTO);
        category = categoryRepository.save(category);
        return CategoryEntityDTOMapper.convertCategoryToCategoryResponseDTO(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(CreateCategoryRequestDTO categoryRequestDTO, UUID categoryId) {
        return null;
    }

    @Override
    public boolean deleteCategory(UUID categoryId) {
        return false;
    }

    @Override
    public double getTotalPriceForCategory(UUID categoryId) {
//example to display multiple when and then can be called inside same method, as we have 2 dependencies
        //List<FakeStoreProductResponseDTO> productResponseDTOS = fakeStoreClient.getAllProducts();
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException("Category for the given is not Found"));
        if(category.getProducts() == null || category.getProducts().isEmpty()){
            return 0;
        }
        else {
            double sum  = 0;
            for (Product p: category.getProducts()){
                sum = sum + p.getPrice();

            }
            return sum;
        }
    }
}
