package zw.hitrac.hwosync.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zw.hitrac.hwosync.model.GeneralParameter;


@Repository
public interface GeneralParametersRepository extends CrudRepository<GeneralParameter, Long> {
}
