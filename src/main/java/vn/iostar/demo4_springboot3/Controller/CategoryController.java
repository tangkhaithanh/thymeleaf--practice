package vn.iostar.demo4_springboot3.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import vn.iostar.demo4_springboot3.Service.CategoryService;
import vn.iostar.demo4_springboot3.Models.Entity.CategoryEntity;
import java.util.Optional;

@Controller
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public String listCategories(Model model, 
                                 @RequestParam(defaultValue = "") String keyword,
                                 @RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "5") int size) {
        Page<CategoryEntity> categoryPage = categoryService.getCategories(keyword, page, size);
        model.addAttribute("categories", categoryPage.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", categoryPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "category/list";
    }

    @GetMapping("/categories/new")
    public String showCategoryForm(Model model) {
        model.addAttribute("category", new CategoryEntity());
        return "category/form";
    }

    @PostMapping("/categories/save")
    public String saveCategory(@ModelAttribute CategoryEntity category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/categories/edit/{id}")
    public String editCategory(@PathVariable Long id, Model model) {
        Optional<CategoryEntity> category = categoryService.getCategoryById(id);
        if (category.isPresent()) {
            model.addAttribute("category", category.get());
            return "category/form";
        }
        return "redirect:/categories";
    }

    @GetMapping("/categories/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
