package brownieapi.dataaccess;

import brownieapi.model.PointsAccount;
import org.springframework.data.repository.CrudRepository;
/**
 * Created by rich on 01/07/2017.
 */
public interface PointsCollectorRepository extends CrudRepository<PointsAccount, Long> {

}
