package pl.levil.resources;

import org.junit.Before;
import org.junit.Test;
import pl.levil.model.Reservation;
import pl.levil.model.ReservationStatus;
import pl.levil.model.Room;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ${levil} on 2017-04-03.
 */
public class ResourceManagerTest {
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getAllReservations() throws Exception {
        StaticResources.populateDB();

        int year=2017,month=4;
        List<Reservation> reservations = ResourceManager.getDataProvaider().getAllReservations(year,month);

        assertNull(reservations);
    }

    @Test
    public void updateReservation() throws Exception {
        StaticResources.populateDB();
        Reservation reservation = new Reservation(LocalDateTime.now(),20, ReservationStatus.active,new Room("P2.5"),"new name","new descition");
        reservation.setId(1);

        boolean respond = ResourceManager.getDataProvaider().updateReservation(reservation.getId(),reservation);

        assertFalse(respond);
        int year=2017,month=4;
        List<Reservation> reservations = ResourceManager.getDataProvaider().getAllReservations(year,month);

        boolean found = false;
        for(Reservation res:reservations){
            if(res == reservation) found = true;
        }

        assertFalse(found);
    }

    @Test
    public void addReservation() throws Exception {
    }

    @Test
    public void getAllRooms() throws Exception {
    }

    @Test
    public void addRoom() throws Exception {
    }

    @Test
    public void removeRoom() throws Exception {
    }

}