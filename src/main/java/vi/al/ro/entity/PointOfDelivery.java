package vi.al.ro.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Пункт выдачи
 */
@Entity
@Table(name="POINT_OF_DELIVERY", schema="public")
public class PointOfDelivery {

    @Id
    @Column(name = "POINT_OF_DELIVERY_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "POINT_OF_DELIVERY_ADDRESS", nullable = false)
    private String address;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "point")
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
