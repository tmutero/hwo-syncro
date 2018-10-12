package zw.hitrac.hwosync.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zw.hitrac.hwosync.model.UserProfile;
import zw.hitrac.hwosync.repository.UserProfileRepository;
import zw.hitrac.hwosync.service.UserProfileService;

import java.util.List;

@Service
public class UserProfileServiceImpl implements UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;
    @Override
    public UserProfile save(UserProfile userProfile) {
        return userProfileRepository.save(userProfile);
    }

    @Override
    public List<UserProfile> findAll() {
        return (List<UserProfile>) userProfileRepository.findAll();
    }

    @Override
    public UserProfile findOne(Long id) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(UserProfile userProfile) {
        return null;
    }

    @Override
    public void remove(UserProfile userProfile) {

    }
}
