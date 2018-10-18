package vi.al.ro.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Группа товаров
 */
@Entity
@Table(name="GROUP_PRODUCT", schema="public")
public class GroupProduct {

    @Id
    @Column(name = "GROUP_PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "GROUP_PRODUCT_NAME", nullable = false, length = 50)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "group")
    private Set<Product> products = new HashSet<>();

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

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
