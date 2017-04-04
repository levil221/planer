package pl.levil.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;


/**
 * Created by mozam on 2017-03-29.
 */
@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private LocalDateTime date;
    @Column
    private int duration;
    @Column
    private ReservationStatus status;
    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;
    @Column
    private String name;
    @Column
    private String description;

    public Reservation(LocalDateTime date, int duration, ReservationStatus status, Room room, String name, String description) {
        this.date = date;
        this.duration = duration;
        this.status = status;
        this.room = room;
        this.name = name;
        this.description = description;
    }

    public Reservation() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
