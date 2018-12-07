package vi.al.ro.entity.dto;

/**
 * DTO
 * Товар
 */
public class ProductDTO {

    private Integer id;

    private String name;

    private GroupProductDTO group;

    private OrderPosDTO position;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GroupProductDTO getGroup() {
        return group;
    }

    public void setGroup(GroupProductDTO group) {
        this.group = group;
    }

    public OrderPosDTO getPosition() {
        return position;
    }

    public void setPosition(OrderPosDTO position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
