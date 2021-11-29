package ee.mihkel.webshopbackend.controller;

import ee.mihkel.webshopbackend.model.Category;
import ee.mihkel.webshopbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping("categories")
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    @PostMapping("categories")
    public void addCategory(@RequestBody Category category) {
       categoryRepository.save(category);
    }

    @DeleteMapping("categories/{categoryId}")
    public void deleteCategory(@PathVariable Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
