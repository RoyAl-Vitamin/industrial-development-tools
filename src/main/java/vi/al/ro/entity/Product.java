package vi.al.ro.entity;

import javax.persistence.*;

/**
 * Товар
 */
@Entity
@Table(name="PRODUCT", schema="public")
public class Product {

    @Id
    @Column(name = "PRODUCT_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 50)
    private String name;

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

    @Override
    public String toString() {
        return "id = " + id + " name = " + name;
    }
}