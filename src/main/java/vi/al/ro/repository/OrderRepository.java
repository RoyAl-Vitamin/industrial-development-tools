package vi.al.ro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vi.al.ro.entity.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
}
