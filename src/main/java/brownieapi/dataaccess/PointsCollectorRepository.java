package brownieapi.dataaccess;

import brownieapi.model.PointsAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

/**
 * Created by rich on 01/07/2017.
 */
public interface PointsCollectorRepository extends CrudRepository<PointsAccount, Long> {

}
