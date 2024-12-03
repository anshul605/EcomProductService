package dev.anshul.EcomProductService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CreateProductRequestDTO {
    private String title;
    private double price;
    private String description;
    //this we will not need--when dto concept come private String category;//if its product class then it should category object here its category name
    private String imageURL;
    private double rating;
    //we will pass the id of the category and if user selects the new category then front end will pass the id
    private UUID categoryId;
}
