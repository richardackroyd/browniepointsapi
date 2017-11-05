package brownieapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by rich on 01/07/2017.
 */

@Entity
public class PointsAccount {

//    @Table(name = "POINTS_ACCOUNT")
//    @JsonIgnoreProperties(ignoreUnknown = true)

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    //private Long id;
    private int points;
    private String name;


/**    public Long getID() {

        return id;

    }

    public void setID(final Long id) {

        this.id = id;

    }**/

    public int getPoints() {

        return points;

    }

    public void setPoints(final int points) {

        this.points = points;

    }

    public String getName() {

        return name;

    }

    public void setName(final String name) {

        this.name = name;

    }

}
