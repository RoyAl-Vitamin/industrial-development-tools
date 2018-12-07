package vi.al.ro.entity.dto;

import vi.al.ro.entity.Order;

import java.util.Set;

/**
 * Позиция заказа
 */
public class OrderPosDTO {

    private Integer id;

    /**
     * Цена
     */
    private Double price;

    /**
     * Скидка
     */
    private Double discount;

    /**
     * Количество
     */
    private Integer quantity;

    /**
     * Описание позиции товара
     */
    private String description;

    private Order order;

    private Set<ProductDTO> products;

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

    public Set<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductDTO> products) {
        this.products = products;
    }
}
