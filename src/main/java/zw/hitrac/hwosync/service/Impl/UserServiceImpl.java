package zw.hitrac.hwosync.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.hitrac.hwosync.model.User;
import zw.hitrac.hwosync.repository.UserRepository;
import zw.hitrac.hwosync.service.UserService;


import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {
 @Autowired
 private UserRepository userRepository;

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public Optional<User> findOne(Long id) {

    return Optional.ofNullable(userRepository.findById(id).get());

  }

  @Override
  public Optional<List<User>> findAll() {
    return Optional.ofNullable((List<User>) userRepository.findAll());
  }

  @Override
  public void delete(Long id) {

    User user= userRepository.findById(id).get();
    userRepository.delete(user);
  }

  @Override
  public Boolean checkDuplicate(User user) {
    return null;
  }
}
