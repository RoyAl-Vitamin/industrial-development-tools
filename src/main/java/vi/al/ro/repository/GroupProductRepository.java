package vi.al.ro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vi.al.ro.entity.GroupProduct;

public interface GroupProductRepository extends PagingAndSortingRepository<GroupProduct, Integer> {
}
