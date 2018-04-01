package brownieapi.apicontroller;

import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.PointsAccount;
import brownieapi.model.AddPointsToAccount;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api")
@Api(value="Manage Points Accounts", description="Creation of a points account and transactional processing e.g. add / remove point")
public class PointsController {

    @Autowired
    private PointsCollectorRepository repositoryOfPointsAccounts;

    @RequestMapping(value = "/points", method = RequestMethod.GET)
    @ApiOperation(value="List of all accounts on the system")
    public List<PointsAccount> GetPointsAccounts() {

        return StreamSupport.stream(repositoryOfPointsAccounts.findAll().spliterator(),
                false).collect(Collectors.toList());

    }

    @RequestMapping(value = "/points/{id}",  method = RequestMethod.GET)
    @ApiOperation(value="Get a specific account")
    public PointsAccount GetPointsAccount (@PathVariable Long id) {

        PointsAccount individualPointsAccount = repositoryOfPointsAccounts.findOne(id);

        if (individualPointsAccount == null) return setPointsAccountNullValues();

        return individualPointsAccount;
    }

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST)
    @ApiOperation(value="Create a points account")
    public PointsAccount CreatePointsAccount(@RequestBody PointsAccount pointsAccount) {

        return repositoryOfPointsAccounts.save(pointsAccount);

    }

    //TODO consider whether should return the object from crudrepository.save in case has changed
    @RequestMapping(value = "/addPoint/{id}", method = RequestMethod.PUT)
    @ApiOperation(value="Increase points on an account")
    public PointsAccount AddPointsToAccount(@PathVariable Long id, @RequestBody AddPointsToAccount pointsToAddToAccount) {

        PointsAccount startingPointsAccount = repositoryOfPointsAccounts.findOne(id);

        System.out.println("Passed in: " + pointsToAddToAccount.getChangeByPoints());

        int updatedPoints = startingPointsAccount.getPoints() + pointsToAddToAccount.getChangeByPoints();

        startingPointsAccount.setPoints(updatedPoints);

        repositoryOfPointsAccounts.save(startingPointsAccount);

        return startingPointsAccount;

    }

    //TODO consider whether should return the object from crudrepository.save in case has changed
    @RequestMapping(value = "/removePoint/{id}", method = RequestMethod.PUT)
    @ApiOperation(value="Decrease points on an account")
    public PointsAccount RemovePointsToAccount(@PathVariable Long id, @RequestBody AddPointsToAccount pointsToAddToAccount) {

        PointsAccount startingPointsAccount = repositoryOfPointsAccounts.findOne(id);

        int updatedPoints = startingPointsAccount.getPoints() - pointsToAddToAccount.getChangeByPoints();

        startingPointsAccount.setPoints(updatedPoints);

        repositoryOfPointsAccounts.save(startingPointsAccount);

        return startingPointsAccount;

    }

    private PointsAccount setPointsAccountNullValues() {

        return new PointsAccount(0, "No data");
    }

}
