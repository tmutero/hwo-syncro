package zw.hitrac.hwosync.repository;

import org.springframework.data.repository.CrudRepository;
import zw.hitrac.hwosync.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
