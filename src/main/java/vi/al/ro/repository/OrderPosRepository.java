package vi.al.ro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vi.al.ro.entity.OrderPos;

public interface OrderPosRepository extends PagingAndSortingRepository<OrderPos, Integer> {
}
