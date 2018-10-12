package zw.hitrac.hwosync.repository;

import org.springframework.data.repository.CrudRepository;
import zw.hitrac.hwosync.model.RegistryCredentials;



public interface RegistryCredentialsRepository  extends CrudRepository<RegistryCredentials, Long> {

    RegistryCredentials findByName (String name);
}
