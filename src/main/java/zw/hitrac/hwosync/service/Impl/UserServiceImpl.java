package zw.hitrac.hwosync.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import zw.hitrac.hwosync.model.User;
import zw.hitrac.hwosync.repository.UserRepository;
import zw.hitrac.hwosync.service.UserService;


import java.util.List;


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
  public List<User> findAll() {
    return (List<User>)userRepository.findAll();
  }

  @Override
  public User findOne(Long id) {
    return null;
  }

  @Override
  public Boolean checkDuplicate(User user) {
    return null;
  }

  @Override
  public void remove(User user) {

  }
}
