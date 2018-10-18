package vi.al.ro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vi.al.ro.entity.OrderPos;

public interface OrderPosRespository extends PagingAndSortingRepository<OrderPos, Integer> {
}
