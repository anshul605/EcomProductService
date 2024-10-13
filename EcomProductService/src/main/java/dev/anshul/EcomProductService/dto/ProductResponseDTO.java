package dev.anshul.EcomProductService.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ProductResponseDTO {
    private int productID;
    private String title;
    private double price;
    private String description;
    private String category;//if its product class then it should category object here its category name
    private String imageURL;
    private double rating;
}


