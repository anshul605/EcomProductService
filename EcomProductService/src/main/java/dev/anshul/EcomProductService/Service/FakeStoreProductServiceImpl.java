package dev.anshul.EcomProductService.Service;

import dev.anshul.EcomProductService.client.FakeStoreClient;
import dev.anshul.EcomProductService.dto.fakeSoreDtos.FakeStoreProductResponseDTO;
import dev.anshul.EcomProductService.entity.Product;
import dev.anshul.EcomProductService.exception.NoProductPresentException;
import dev.anshul.EcomProductService.exception.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("fakeStoreProductService")
public class FakeStoreProductServiceImpl {
//implements ProductService-- remove this so that we dont have to update it here based on the changes in product service interface
    @Autowired
    private FakeStoreClient fakeStoreClient;

    //@Override
    public List<FakeStoreProductResponseDTO> getAllProducts() {
        List<FakeStoreProductResponseDTO> fakeStoreProducts =  fakeStoreClient.getAllProducts();
        if (fakeStoreProducts == null){
            throw new NoProductPresentException("No Products are found");
        }
        return fakeStoreProducts;
    }

    //@Override
    public FakeStoreProductResponseDTO getProduct(int productId) throws ProductNotFoundException {
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO = fakeStoreClient.getProductByID(productId);
        if(fakeStoreProductResponseDTO == null){
            throw new ProductNotFoundException("Product not found with id:" + productId);
        }

        return fakeStoreProductResponseDTO;
    }

    //@Override
    public Product createProduct(Product product) {
        return null;
    }

    //@Override
    public Product updateProduct(Product updatedProduct, int productID) {
        return null;
    }

    //@Override
    public boolean deleteProduct(int productID) {
        return false;
    }
}
