package dev.anshul.EcomProductService.service;

import dev.anshul.EcomProductService.Service.CategoryServiceImpl;
import dev.anshul.EcomProductService.Service.ProductService;
import dev.anshul.EcomProductService.entity.Category;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.CategoryNotFoundException;
import dev.anshul.EcomProductService.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;
//wherever we have the logical code need to write test case--not required for entity  , models etc.
//spring uses Jnuite for unit test internally
public class CategoryServiceImplTest {
    //mock all the dependencies of the class we are testing
    @Mock
    private CategoryRepository categoryRepository;

    // in which class i want to inject these mocks
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach

    //i want to initialise all the things again and again before every method
    //for every test case it will run the setup. if we put before it will run only once
    public void setup(){
        MockitoAnnotations.initMocks(this);
        //when you start running the code, initiliase all mock object or annotation @mock should be initialised inside this test class.
        //not required nowadays, it initiliases and adds all the required mocks. all the mock object are created in to object and then inject into setup of the class
    }
    // so this is done only once right..depends what do you want. i want to play around the object and the object might change some value and want to
    //start again with the scratch you can use before each
    // if we put before it will run only once i.e same object will be used again. for database coz data will remain same we can use before
    //test method all public void

    @Test
    public void testGetTotalPriceForMultipleProductsUnderCategory(){
        //this method will return the total cost of all products under a category
        //AAA ru
        //Arrange
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockData();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        //mockito is the object that run around with your tests
        //now calling the auctual method of the class
        double expectedTotalCost = 300.00;

        //Act
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);
        //Assert (all checks)
        Assertions.assertEquals(expectedTotalCost, actualTotalCost);
    }

    @Test
    public void testGetTotalPriceForZeroProductsUnderCategory(){
        //this method will return the total cost of all products under a category
        UUID categoryId = UUID.randomUUID();
        Optional<Category> categoryOptionalMockData = getCategoryMockDataWithZeroProducts();
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(categoryOptionalMockData);
        //mockito is the object that run around with your tests
        //now calling the auctual method of the class
        double actualTotalCost = categoryService.getTotalPriceForCategory(categoryId);
        double expectedTotalCost = 0.0;

        //if in actual method before calling the repo layer if we return 0 directly then it will get passed, we need to verifying
        //actual things we mocked is getting called or not

        //if either of the check fails the test will fail
        Assertions.assertEquals(expectedTotalCost, actualTotalCost);

        Mockito.verify(categoryRepository).findById(categoryId);
    }

    private Optional<Category> getCategoryMockDataWithZeroProducts() {
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");
        List<Product> products = new ArrayList<>();
        category.setProducts(products);
        return Optional.of(category);
    }

    @Test
    public void testCategoryNotFoundExceptionThrown(){
        //Arrange
        UUID categoryId = UUID.randomUUID();
        //if i have to throw excepton that means category optional we will get will be empty. coz orelsethrow, throw excpetion when optional is empty
        Mockito.when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        //when you throw exception need to combine act and assert
        //Act and //Assert
        Assertions.assertThrows(CategoryNotFoundException.class,
                () -> categoryService.getTotalPriceForCategory(categoryId));

    }
        //if you see you need this mock data in 3 -4 classes you can create in seperate class
    //repo returns find by id is optional
    private Optional<Category> getCategoryMockData(){
        Category category = new Category();
        category.setId(UUID.randomUUID());
        category.setName("CategoryName");

        Product product1 = new Product();
        product1.setId(UUID.randomUUID());
        product1.setTitle("Product1");
        product1.setPrice(100.00);
        //we have category as object
        product1.setCategory(category);

        Product product2 = new Product();
        product2.setId(UUID.randomUUID());
        product2.setTitle("Product2");
        product2.setPrice(200.00);
        product2.setCategory(category);

        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        category.setProducts(products);

        return Optional.of(category);
    }


}




