package vi.al.ro.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import vi.al.ro.entity.Product;

import java.util.List;

public interface ProductRepository extends PagingAndSortingRepository<Product, Integer> {

    @Query("SELECT T FROM Product T WHERE T.name LIKE CONCAT('%', :text, '%')")
    List<Product> findByNameLike(String text);
}
