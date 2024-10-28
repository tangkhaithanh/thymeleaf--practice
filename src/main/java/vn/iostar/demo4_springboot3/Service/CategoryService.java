package vn.iostar.demo4_springboot3.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.iostar.demo4_springboot3.Repository.CategoryRepository;
import vn.iostar.demo4_springboot3.Models.Entity.CategoryEntity;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public Page<CategoryEntity> getCategories(String keyword, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        if (keyword != null) {
            return categoryRepository.findByNameContaining(keyword, pageable);
        }
        return categoryRepository.findAll(pageable);
    }

    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    public Optional<CategoryEntity> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }
}
