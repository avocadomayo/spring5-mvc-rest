package ca.wendyliu.springframework.service;

import ca.wendyliu.spring5mvcrest.api.v1.mapper.CategoryMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.CategoryDTO;
import ca.wendyliu.spring5mvcrest.domain.Category;
import ca.wendyliu.spring5mvcrest.repository.CategoryRepository;
import ca.wendyliu.spring5mvcrest.service.CategoryService;
import ca.wendyliu.spring5mvcrest.service.CategoryServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

public class CategoryServiceTest {

    public static final Long ID = 2L;
    public static final String NAME = "Wendy";
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        categoryService = new CategoryServiceImpl(CategoryMapper.INSTANCE, categoryRepository);
    }

    @Test
    public void getAllCategories() throws Exception {
        // Given
        List<Category> categories = Arrays.asList(new Category(), new Category(), new Category());
        when(categoryRepository.findAll()).thenReturn(categories);

        // When
        List<CategoryDTO> categoryDTOs = categoryService.getAllCategories();

        // Then
        assertEquals(3, categoryDTOs.size());
    }

    @Test
    public void getCategoryByName() throws Exception {
        // Given
        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);

        when(categoryRepository.findByName(anyString())).thenReturn(category);

        // When
        CategoryDTO categoryDTO = categoryService.getCategoryByName(NAME);

        // Then
        assertEquals(ID, categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }
}
