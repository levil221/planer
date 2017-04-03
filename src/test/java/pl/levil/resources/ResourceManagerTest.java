package pl.levil.resources;

import org.junit.Before;
import org.junit.Test;
import pl.levil.model.Reservation;

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

        assertNotNull(reservations);
    }

    @Test
    public void updateReservation() throws Exception {
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