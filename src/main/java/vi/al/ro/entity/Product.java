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
    @TableGenerator(name = "product_generator", table = "table_product_generator", initialValue = 9, allocationSize = 200)
    @GeneratedValue(generator = "product_generator")
    private Integer id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_PRODUCT_ID", nullable = false)
    private GroupProduct group;

    @ManyToOne(targetEntity = OrderPos.class)
    @JoinColumn(name="ORDER_POSITION_ID", nullable = true)
    private OrderPos position;

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

    public GroupProduct getGroup() {
        return group;
    }

    public void setGroup(GroupProduct group) {
        this.group = group;
    }

    public OrderPos getPosition() {
        return position;
    }

    public void setPosition(OrderPos position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
