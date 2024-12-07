package dev.anshul.EcomProductService.controller;

import dev.anshul.EcomProductService.Controller.CartController;
import dev.anshul.EcomProductService.Controller.CategoryController;
import dev.anshul.EcomProductService.Service.CategoryService;
import dev.anshul.EcomProductService.dto.CategoryResponseDTO;
import dev.anshul.EcomProductService.dto.CreateCategoryRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class CategoryControllerTest {
    //if we have 2 implementation of category service then not need Qualifier for unit testing
    //as we are mocking the data directly not calling the extending the interface class

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testUpdateCategorySuccess(){
        //we can copy behaviours, dto we cannot mock/copy. and object are the one that is compraed.
        //arrange
        CreateCategoryRequestDTO updateRequestDTO = new CreateCategoryRequestDTO();
        updateRequestDTO.setCategoryName("NewCategoryName");
        UUID randomUID = UUID.randomUUID();

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(randomUID);
        categoryResponseDTO.setCategoryName("NewCategoryName");

        Mockito.when(categoryService.updateCategory(updateRequestDTO, randomUID)).thenReturn(categoryResponseDTO);

        //ACt
        //here we are calling the method directly, we never call controller method directly we call it via API
        // this is not called by us it is called by restAPIs or API--now we will test integration tesst
        ResponseEntity<CategoryResponseDTO> categoryResponseEntity = categoryController.updateCategory(randomUID, updateRequestDTO);

        //Assert (we can compare object as well)

        Assertions.assertEquals(categoryResponseEntity.getBody(), categoryResponseDTO);


    }



}
