package vi.al.ro.entity.dto;

import vi.al.ro.entity.Order;

import java.util.HashSet;
import java.util.Set;

/**
 * DTO
 * Пункт выдачи
 */
public class PointOfDeliveryDTO {

    private Integer id;

    private String address;

    private Set<Order> order = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }
}
