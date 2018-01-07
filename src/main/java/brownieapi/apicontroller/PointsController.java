package brownieapi.apicontroller;

import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.PointsAccount;
import brownieapi.model.AddPointsToAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
public class PointsController {

    @Autowired
    private PointsCollectorRepository repositoryOfPointsAccounts;

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    public List<PointsAccount> GetPointsAccounts() {

        return StreamSupport.stream(repositoryOfPointsAccounts.findAll().spliterator(),
                false).collect(Collectors.toList());

    }

    @RequestMapping(value = "/points/{id}",  method = RequestMethod.GET)
    public PointsAccount GetPointsAccount (@PathVariable Long id) {

        PointsAccount individualPointsAccount = repositoryOfPointsAccounts.findOne(id);

        if (individualPointsAccount == null) return setPointsAccountNullValues();

        return individualPointsAccount;
    }

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST)
    public PointsAccount CreatePointsAccount(@RequestBody PointsAccount pointsAccount) {

        return repositoryOfPointsAccounts.save(pointsAccount);

    }

    //TODO consider whether should return the object from crudrepository.save in case has changed
    @RequestMapping(value = "/addPoint/{id}", method = RequestMethod.PUT)
    public int AddPointsToAccount(@PathVariable Long id, @RequestBody AddPointsToAccount pointsToAddToAccount) {

        PointsAccount startingPointsAccount = repositoryOfPointsAccounts.findOne(id);

        System.out.println("Passed in: " + pointsToAddToAccount.getChangeByPoints());

        int updatedPoints = startingPointsAccount.getPoints() + pointsToAddToAccount.getChangeByPoints();

        startingPointsAccount.setPoints(updatedPoints);

        repositoryOfPointsAccounts.save(startingPointsAccount);

        return startingPointsAccount.getPoints();

    }

    //TODO consider whether should return the object from crudrepository.save in case has changed
    @RequestMapping(value = "/removePoint/{id}", method = RequestMethod.PUT)
    public int RemovePointsToAccount(@PathVariable Long id, @RequestBody AddPointsToAccount pointsToAddToAccount) {

        PointsAccount startingPointsAccount = repositoryOfPointsAccounts.findOne(id);

        int updatedPoints = startingPointsAccount.getPoints() - pointsToAddToAccount.getChangeByPoints();

        startingPointsAccount.setPoints(updatedPoints);

        repositoryOfPointsAccounts.save(startingPointsAccount);

        return startingPointsAccount.getPoints();

    }

    private PointsAccount setPointsAccountNullValues() {

        return new PointsAccount(0, "No data");
    }

}
