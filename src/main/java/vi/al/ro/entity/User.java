package vi.al.ro.entity;

import javax.persistence.*;

/**
 * Пользователь
 */
@Entity
@Table(name="USER", schema="public")
public class User {

    @Id
    @Column(name = "USER_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "USER_FULL_NAME", nullable = false, length = 64)
    private String fullname;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                '}';
    }
}
