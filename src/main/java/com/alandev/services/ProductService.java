package com.alandev.services;

import com.alandev.dto.CategoryDTO;
import com.alandev.dto.ProductDTO;
import com.alandev.entities.Category;
import com.alandev.entities.Product;
import com.alandev.repositories.CategoryRepository;
import com.alandev.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private CategoryRepository categoryRepository;

    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();

        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());

        for (CategoryDTO categoryDTO : dto.getCategories()) {
            Category cat = categoryRepository.getReferenceById(categoryDTO.getId());
            cat.setId(categoryDTO.getId());
            entity.getCategories().add(cat);
        }
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }
}
