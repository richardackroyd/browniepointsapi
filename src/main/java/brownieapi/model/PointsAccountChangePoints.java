package brownieapi.model;

import javax.persistence.*;

/**
 * Created by rich on 01/07/2017.
 */

public class PointsAccountChangePoints {

    private Long idToChange;

    private int changeByPoints;

    public PointsAccountChangePoints() {

    }

    public PointsAccountChangePoints(Long idToChange, int changeByPoints) {

        this.idToChange = idToChange;
        this.changeByPoints = changeByPoints;

    }


    public Long getIdToChange() {

        return idToChange;

    }

    public void setIdToChange(final Long idToChange) {

        this.idToChange = idToChange;

    }

    public int getChangeByPoints() {

        return changeByPoints;

    }

    public void setChangeByPoints(final int changeByPoints) {

        this.changeByPoints = changeByPoints;

    }

}
