package vi.al.ro.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import vi.al.ro.entity.User;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
}
