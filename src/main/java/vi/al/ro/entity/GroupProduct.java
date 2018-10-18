package vi.al.ro.entity;

import javax.persistence.*;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
