package brownieapi.apicontroller;

import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.PointsAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class PointsController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PointsCollectorRepository repositoryOfPointsAccounts;

    @RequestMapping("/points")
    public List<PointsAccount> GetPointsAccounts() {

        final List<PointsAccount> accounts = StreamSupport.stream(repositoryOfPointsAccounts.findAll().spliterator(),
                false).collect(Collectors.toList());

        return accounts;

    }

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST)
    public PointsAccount CreatePointsAccount(@RequestBody PointsAccount pointsAccount) {

        PointsAccount returnedPointsAccount = new PointsAccount();

        returnedPointsAccount = repositoryOfPointsAccounts.save(pointsAccount);

        return returnedPointsAccount;

    }

    @RequestMapping("/points/{id}")
    public PointsAccount GetPointsAccount (@PathVariable Long id) {

        PointsAccount indiviualPointsAccount = new PointsAccount();
        indiviualPointsAccount = repositoryOfPointsAccounts.findOne(id);

        if (indiviualPointsAccount == null) {
            return setPointsAccountNullValues();
        }

        return indiviualPointsAccount;
    }

    private PointsAccount setPointsAccountNullValues() {

        PointsAccount returnPoints = new PointsAccount(0, "No data");
        return returnPoints;
    }

}
