package zw.hitrac.hwosync.service.Impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import zw.hitrac.hwosync.model.GeneralParameter;
import zw.hitrac.hwosync.model.User;
import zw.hitrac.hwosync.repository.GeneralParametersRepository;
import zw.hitrac.hwosync.service.GeneralParametersService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;


@Service

public class GeneralParametersServiceImpl implements GeneralParametersService {

    @Autowired
    private GeneralParametersRepository generalParametersRepository;


    @Override
    public GeneralParameter save(GeneralParameter generalParameter) {
        return generalParametersRepository.save(generalParameter);
    }

    @Override
    public Optional<GeneralParameter> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<GeneralParameter>> findAll() {
        return  Optional.ofNullable((List<GeneralParameter>) generalParametersRepository.findAll());
    }

    @Override
    public void delete(Long id) {
       GeneralParameter generalParameter= generalParametersRepository.findById(id).get();
        generalParametersRepository.delete(generalParameter);

    }

    @Override
    public Boolean checkDuplicate(GeneralParameter generalParameter) {
        return null;
    }

    @Override
    public GeneralParameter findByCouncil(String council) {
        return generalParametersRepository.findByCouncil(council);
    }
}
