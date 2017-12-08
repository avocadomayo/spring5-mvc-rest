package ca.wendyliu.spring5mvcrest.controller.v1;

import ca.wendyliu.spring5mvcrest.api.v1.model.CategoryDTO;
import ca.wendyliu.spring5mvcrest.api.v1.model.CategoryListDTO;
import ca.wendyliu.spring5mvcrest.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// Available since Spring 4.0. Convenience annotation that includes @ResponseBody. Use this - this is the most modern
// architecture.
// Convenience annotation that says we're returning @ResponseBody from these methods. Spring framework will handle
// parsing it to the desired format (XML, JSON, whatever). Since we're requesting JSON, Jackson will return a generated
// JSON to user. In this case, CategoryListDTO and CategoryDTO will be converted to JSON.
@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getAllCategories() {
        return new CategoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name) {
        return categoryService.getCategoryByName(name);
    }
}
