package brownieapi.apicontroller;

import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.PointsAccount;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class PointsController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private DataSource dataSource;

    private final PointsCollectorRepository repositoryOfPointsAccounts;

    @Autowired
    public PointsController(final PointsCollectorRepository repository) {
        this.repositoryOfPointsAccounts = repository;
    }

    @RequestMapping("/points")
    public List<PointsAccount> PointsAccount() {

        final List<PointsAccount> accounts = StreamSupport.stream(repositoryOfPointsAccounts.findAll().spliterator(),
                false).collect(Collectors.toList());

        return accounts;

    }

    @RequestMapping("/points/{id}")
    public PointsAccount PointsAccountSingle (@PathVariable Long id) {

        PointsAccount indiviualPointsAccount = new PointsAccount();
        indiviualPointsAccount = repositoryOfPointsAccounts.findOne(id);

        if (indiviualPointsAccount == null) {
            return setPointsAccountNullValues();
        }

        return indiviualPointsAccount;
    }

    private PointsAccount setPointsAccountNullValues() {

        PointsAccount returnPoints = new PointsAccount();
        returnPoints.setName("No data");
        returnPoints.setID(Long.valueOf(-1));
        returnPoints.setPoints(0);

        return returnPoints;
    }

}
