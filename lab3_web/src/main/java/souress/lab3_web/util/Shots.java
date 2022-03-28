package souress.lab3_web.util;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="shots")
@Data
public class Shots implements Serializable {
    @Id
    @Column(name="shots_id")
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "next_shots_id")
    private int id;

    @Column(name="x")
    private double x;

    @Column(name="y")
    private double y;

    @Column(name="r")
    private double r;

    @Column(name="currentTime")
    private String currentTime;

    @Column(name="duration")
    private long duration;

    @Column(name="result")
    private boolean result;

    @Column(name="sessionId")
    private String sessionId;

    @Override
    public String toString() {
        return "Shots{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", r=" + r +
                ", currentTime='" + currentTime + '\'' +
                ", duration=" + duration +
                ", result=" + result +
                '}';
    }
}
