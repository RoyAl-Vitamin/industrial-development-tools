package vi.al.ro.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Пользователь
 */
@Entity
@Table(name="USER", schema="public")
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false)
    @TableGenerator(name = "user_generator", table = "table_user_generator", initialValue = 15, allocationSize = 200)
    @GeneratedValue(generator = "user_generator")
    private Integer id;

    @Column(name = "USER_FULL_NAME", nullable = false, length = 64)
    private String fullname;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "user")
    private Set<Order> order = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
