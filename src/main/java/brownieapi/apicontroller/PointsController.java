package brownieapi.apicontroller;

import brownieapi.dataaccess.PointsCollectorRepository;
import brownieapi.model.PointsAccount;
import brownieapi.model.PointsAccountChangePoints;
import javafx.scene.effect.Light;
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

    @RequestMapping("/points")
    public List<PointsAccount> GetPointsAccounts() {

        return StreamSupport.stream(repositoryOfPointsAccounts.findAll().spliterator(),
                false).collect(Collectors.toList());

    }

    @RequestMapping("/points/{id}")
    public PointsAccount GetPointsAccount (@PathVariable Long id) {

        PointsAccount individualPointsAccount = repositoryOfPointsAccounts.findOne(id);

        if (individualPointsAccount == null) return setPointsAccountNullValues();

        return individualPointsAccount;
    }

    @RequestMapping(value = "/addEntry", method = RequestMethod.POST)
    public PointsAccount CreatePointsAccount(@RequestBody PointsAccount pointsAccount) {

        return repositoryOfPointsAccounts.save(pointsAccount);

    }

    //TODO update update call to take the ID as @PathVariable to the URI and not as a parameter
    //TODO should this return points int or the actual object
    //TODO consider whether should return the object from crudrepository.save in case has changed
    @RequestMapping(value = "/addPoint/{id}", method = RequestMethod.PUT)
    public int AddPointsToAccount(@PathVariable Long id, @RequestBody PointsAccountChangePoints pointsAccountToChange) {

        System.out.println("The passed id is: " + id);
        
        PointsAccount startingPointsAccount = repositoryOfPointsAccounts.findOne(pointsAccountToChange.getIdToChange());

        int updatedPoints = startingPointsAccount.getPoints() + pointsAccountToChange.getChangeByPoints();

        startingPointsAccount.setPoints(updatedPoints);

        repositoryOfPointsAccounts.save(startingPointsAccount);

        return startingPointsAccount.getPoints();

    }

    private PointsAccount setPointsAccountNullValues() {

        return new PointsAccount(0, "No data");
    }

}
