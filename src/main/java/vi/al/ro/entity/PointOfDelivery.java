package vi.al.ro.entity;

import javax.persistence.*;

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
}
