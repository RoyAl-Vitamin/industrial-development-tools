package vi.al.ro.entity.dto;

/**
 * DTO
 * Товар
 */
public class ProductDTO {

    private Integer id;

    private String name;

    private InnerClassGroupProduct group;

    private InnerClassOrderPosDTO position;

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

    public InnerClassGroupProduct getGroup() {
        return group;
    }

    public void setGroup(InnerClassGroupProduct group) {
        this.group = group;
    }

    public InnerClassOrderPosDTO getPosition() {
        return position;
    }

    public void setPosition(InnerClassOrderPosDTO position) {
        this.position = position;
    }

    public static class InnerClassGroupProduct {

        private Integer id;

        private String name;

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
    }

    public static class InnerClassOrderPosDTO {

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
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
