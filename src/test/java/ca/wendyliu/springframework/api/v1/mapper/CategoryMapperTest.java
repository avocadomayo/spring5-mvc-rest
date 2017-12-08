package ca.wendyliu.springframework.api.v1.mapper;

import ca.wendyliu.spring5mvcrest.api.v1.mapper.CategoryMapper;
import ca.wendyliu.spring5mvcrest.api.v1.model.CategoryDTO;
import ca.wendyliu.spring5mvcrest.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String WENDY = "Wendy";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {
        // Given
        Category category = new Category(WENDY);
        category.setId(ID);

        // When
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        // Then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(WENDY, categoryDTO.getName());
    }
}
