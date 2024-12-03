package dev.anshul.EcomProductService.Controller;

import dev.anshul.EcomProductService.Service.CategoryService;
import dev.anshul.EcomProductService.dto.CreateCategoryRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity getAllCategories(){
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity getCategoryByID(@PathVariable("id") UUID categoryId  ){
        return null;
    }


    @PostMapping
    public ResponseEntity createCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO){
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity updateCategory(@RequestBody CreateCategoryRequestDTO createCategoryRequestDTO, @PathVariable("id") UUID categoryId){
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCategory(@PathVariable("id") UUID categoryId){
        return null;
    }
}
