package brownieapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

/**
 * Created by rich on 01/07/2017.
 */

@Entity
@Table(name = "pointsaccount")
public class PointsAccount {


//    @JsonIgnoreProperties(ignoreUnknown = true)

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "points")
    private int points;

    @Column(name = "name")
    private String name;

    public PointsAccount() {

    }

    public PointsAccount(int points, String name) {

        this.name = name;
        this.points = points;

    }


    public Long getID() {

        return id;

    }

    public void setID(final Long id) {

        this.id = id;

    }

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
