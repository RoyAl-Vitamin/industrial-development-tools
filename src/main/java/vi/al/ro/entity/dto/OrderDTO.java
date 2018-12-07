package vi.al.ro.entity.dto;

import vi.al.ro.entity.OrderPos;
import vi.al.ro.entity.PointOfDelivery;
import vi.al.ro.entity.User;

import java.util.Set;

/**
 * DTO
 * Заказ
 */
public class OrderDTO {

    private Integer id;

    private Set<OrderPos> orderPoses;

    private User user;

    private PointOfDelivery point;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<OrderPos> getOrderPoses() {
        return orderPoses;
    }

    public void setOrderPoses(Set<OrderPos> orderPoses) {
        this.orderPoses = orderPoses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PointOfDelivery getPoint() {
        return point;
    }

    public void setPoint(PointOfDelivery point) {
        this.point = point;
    }
}
