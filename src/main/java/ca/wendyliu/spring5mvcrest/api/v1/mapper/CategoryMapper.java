package ca.wendyliu.spring5mvcrest.api.v1.mapper;

import ca.wendyliu.spring5mvcrest.api.v1.model.CategoryDTO;
import ca.wendyliu.spring5mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/** Maps object Category to a CategoryDTO */
@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDTO categoryToCategoryDTO(Category category);
}
