package ca.wendyliu.spring5mvcrest.api.v1.model;

import lombok.Data;

// @Data sets up getters and setters for DTO
@Data
public class CategoryDTO {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
