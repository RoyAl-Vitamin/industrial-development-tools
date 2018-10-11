package test.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import test.entity.Order;

public interface OrderRepository extends PagingAndSortingRepository<Order, Integer> {
}
