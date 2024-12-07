package dev.anshul.EcomProductService.Controller;

import dev.anshul.EcomProductService.Service.CategoryService;
import dev.anshul.EcomProductService.dto.CategoryResponseDTO;
import dev.anshul.EcomProductService.dto.CreateCategoryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategoryById(@PathVariable("id")UUID categoryId){
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        return ResponseEntity.ok(categoryService.createCategory(createCategoryRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> updateCategory(@PathVariable("id") UUID categoryId, @RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        //when teaching test cases we add some null checks to show existing test should not fail then somehow missed to remove --throws Exception
        return ResponseEntity.ok(categoryService.updateCategory(createCategoryRequestDTO, categoryId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") UUID categoryId){
        return ResponseEntity.ok(categoryService.deleteCategory(categoryId));
    }

    @GetMapping("/totalPrice/{categoryId}")
    public ResponseEntity<Double> getTotalPriceForAllProducts(@PathVariable ("categoryId") UUID categoryId){
        return ResponseEntity.ok(categoryService.getTotalPriceForCategory(categoryId));
    }
}
