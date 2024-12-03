package dev.anshul.EcomProductService.repository;

import dev.anshul.EcomProductService.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepoistory extends JpaRepository<Category, UUID> {
}
