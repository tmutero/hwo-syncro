package zw.hitrac.hwosync.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import zw.hitrac.hwosync.model.RegistryCredentials;
import zw.hitrac.hwosync.repository.RegistryCredentialsRepository;
import zw.hitrac.hwosync.service.RegistryCredentialsService;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RegistryCredentialsServiceImpl implements RegistryCredentialsService {

  @Autowired
  private RegistryCredentialsRepository registryCredentialsRepository;


    @Override
    public RegistryCredentials save(RegistryCredentials registryCredentials) {
        return registryCredentialsRepository.save(registryCredentials);
    }

    @Override
    public List<RegistryCredentials> findAll() {
        return (List<RegistryCredentials>) registryCredentialsRepository.findAll();
    }

    @Override
    public RegistryCredentials findOne(Long id) {
        return null;

    }

    @Override
    public Boolean checkDuplicate(RegistryCredentials registryCredentials) {
        return null;
    }

    @Override
    public void remove(RegistryCredentials registryCredentials) {

    }

    @Override
    public RegistryCredentials findByName(String name) {
        if (!name.isEmpty()) {
            return registryCredentialsRepository.findByName(name);
        } else {
            return null;
        }
    }
}
