package vi.al.ro.entity.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO
 * Группа товаров
 */
public class GroupProductDTO {

    private Integer id;

    private String name;

    private Set<ProductDTO> products = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
