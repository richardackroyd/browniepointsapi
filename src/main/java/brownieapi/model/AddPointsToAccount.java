package brownieapi.model;

import javax.persistence.*;

/**
 * Created by rich on 01/07/2017.
 */

public class AddPointsToAccount {

    private int changeByPoints;

    public AddPointsToAccount() {

    }

    public AddPointsToAccount(int changeByPoints) {

        this.changeByPoints = changeByPoints;

    }

    public int getChangeByPoints() {

        return changeByPoints;

    }

    public void setChangeByPoints(final int changeByPoints) {

        this.changeByPoints = changeByPoints;

    }

}
