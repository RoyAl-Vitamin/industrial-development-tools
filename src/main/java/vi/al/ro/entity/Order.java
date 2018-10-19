package vi.al.ro.entity;

import javax.persistence.*;
import java.util.Set;

/**
 * Заказ
 */
@Entity
@Table(name="ORDER", schema="public")
public class Order {

    @Id
    @Column(name="ORDER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "order") // имя поля!
    private Set<OrderPos> orderPoses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POINT_OF_DELIVERY_ID", nullable = false)
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
