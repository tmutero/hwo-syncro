package zw.hitrac.hwosync.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import zw.hitrac.hwosync.model.GeneralParameter;
import zw.hitrac.hwosync.repository.GeneralParametersRepository;
import zw.hitrac.hwosync.service.GeneralParametersService;

import javax.annotation.Resource;
import java.util.List;


@Service

public class GeneralParametersServiceImpl implements GeneralParametersService {

    @Autowired
    private GeneralParametersRepository generalParametersRepository;

    @Override
    public GeneralParameter save(GeneralParameter  generalParameters) {
        return null;
    }

    @Override
    public List<GeneralParameter> findAll() {
        return null;
    }

    @Override
    public GeneralParameter findOne(Long id) {
        return null;
    }

    @Override
    public Boolean checkDuplicate(GeneralParameter generalParameters) {
        return null;
    }

    @Override
    public void remove(GeneralParameter generalParameters) {

    }
}
