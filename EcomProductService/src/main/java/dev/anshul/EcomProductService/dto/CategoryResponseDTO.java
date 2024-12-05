package dev.anshul.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class CategoryResponseDTO {

    private UUID categoryId;
    private String categoryName;
    //instead of returning the product, need to returning the dto. you need to figure out inside the
    //mapper class need to call the other mapper
    private List<ProductResponseDTO> products;
}
