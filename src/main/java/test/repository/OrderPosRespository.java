package test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.OrderPos;

public interface OrderPosRespository extends PagingAndSortingRepository<OrderPos, Integer> {
}
