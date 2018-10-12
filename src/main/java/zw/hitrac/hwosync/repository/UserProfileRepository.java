package zw.hitrac.hwosync.repository;

import org.springframework.data.repository.CrudRepository;
import zw.hitrac.hwosync.model.UserProfile;

public interface UserProfileRepository  extends CrudRepository<UserProfile, Long> {
}
