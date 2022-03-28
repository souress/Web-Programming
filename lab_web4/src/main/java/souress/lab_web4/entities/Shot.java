package souress.lab_web4.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "shots")
@Data
public class Shot {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name="owner")
    private String owner;

    @Override
    public String toString() {
        return '{' + "\"id\": \"" + id + "\"," +
                "\"x\": \"" + x + "\"," +
                "\"y\": \"" + y + "\"," +
                "\"r\": \"" + r + "\"," +
                "\"currentTime\": \"" + currentTime + "\"," +
                "\"duration\": \"" + duration + "\"," +
                "\"result\": \"" + result + "\"," +
                "\"owner\": \"" + owner + "\"" +
                '}';
    }
}
