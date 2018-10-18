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

    @Column(name="ORDER_PHONE", length = 20)
    private String phone;

    @Column(name="ORDER_REMARK", length = 1000)
    private String remark;

    @OneToMany(mappedBy = "order") // имя поля!
    private Set<OrderPos> orderPoses;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Set<OrderPos> getOrderPoses() {
        return orderPoses;
    }

    public void setOrderPoses(Set<OrderPos> orderPoses) {
        this.orderPoses = orderPoses;
    }
}
