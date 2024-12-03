package dev.anshul.EcomProductService.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import javax.xml.transform.sax.SAXResult;
import java.util.List;

@Entity
@Getter
@Setter
public class Category extends BaseModel {
    private String name;
    @OneToMany
    private List<Product> products;
}
