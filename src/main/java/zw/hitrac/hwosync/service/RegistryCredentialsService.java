package zw.hitrac.hwosync.service;



import zw.hitrac.hwosync.model.RegistryCredentials;

import java.util.List;
import java.util.Optional;

public interface RegistryCredentialsService extends GenericService<RegistryCredentials>{

  RegistryCredentials findByName(String name);

}