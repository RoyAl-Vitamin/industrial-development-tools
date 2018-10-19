package vi.al.ro.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Позиция заказа
 */
@Entity
@Table(name="ORDER_POSITION", schema = "public")
public class OrderPos {

    @Id
    @Column(name="ORDER_POSITION_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Цена
     */
    @Column(name="ORDER_POSITION_PRICE", nullable=false)
    private Double price;

    /**
     * Скидка
     */
    @Column(name="ORDER_POSITION_DISCOUNT", nullable=false)
    private Double discount;

    /**
     * Количество
     */
    @Column(name="ORDER_POSITION_QUANTITY", nullable=false)
    private Integer quantity;

    /**
     * Описание позиции товара
     */
    @Column(name="ORDER_POSITION_DESCRIPTION", nullable=false)
    private String description;

    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name="ORDER_ID", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "position") // имя поля!
    private Set<Product> products;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
