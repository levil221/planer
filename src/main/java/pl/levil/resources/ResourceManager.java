package pl.levil.resources;

import pl.levil.model.Reservation;
import pl.levil.model.Room;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by ${levil} on 2017-04-03.
 */
public class ResourceManager {
    private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("planerDB");

    public static EntityManagerFactory getEntityManagerFactory() {
        return entityManagerFactory;
    }

    private static ResourceManager ourInstance = new ResourceManager();

    public static ResourceManager getDataProvaider() {
        return ourInstance;
    }

    private ResourceManager() {
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        entityManagerFactory.close();
    }

    //region Reservations region

    public List<Reservation> getAllReservations(int year, int month){
        Reservation res = new Reservation();
        List<Reservation> reservations = null;


        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Reservation> reservationCriteriaQuery = criteriaBuilder.createQuery(Reservation.class);
            Root<Reservation> reservationRoot = reservationCriteriaQuery.from(Reservation.class);


            reservationCriteriaQuery.select(reservationRoot).
                    where(
                            criteriaBuilder.equal(criteriaBuilder.function(
                                    "year",Integer.class,reservationRoot.get("date")),year
                            ),
                            criteriaBuilder.equal(criteriaBuilder.function(
                                    "month",Integer.class,reservationRoot.get("date")),month
                            )
                    );

            reservations = entityManager.createQuery(reservationCriteriaQuery).getResultList();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }

        return reservations;
    }

    public boolean updateReservation(int reservationID, Reservation send){

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            Reservation reservation = entityManager.find(Reservation.class,reservationID);

            reservation.setDate(send.getDate());
            reservation.setDescription(send.getDescription());
            reservation.setDuration(send.getDuration());
            reservation.setName(send.getName());
            reservation.setRoom(send.getRoom());
            reservation.setStatus(send.getStatus());

            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    public boolean addReservation(Reservation send){

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            entityManager.persist(send);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    public boolean removeReservation(int reservationID){

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            Reservation toRemove = entityManager.find(Reservation.class,reservationID);
            entityManager.remove(toRemove);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
        finally {
            entityManager.close();
        }

        return true;
    }
    //endregion regiomn region

    //region Rooms region
    public List<Room> getAllRooms(){

        List<Room> rooms = null;

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Room> roomCriteriaQuery = criteriaBuilder.createQuery(Room.class);
            Root<Room> roomsRoot = roomCriteriaQuery.from(Room.class);

            roomCriteriaQuery.select(roomsRoot);

            rooms = entityManager.createQuery(roomCriteriaQuery).getResultList();
        }
        catch (Exception ex){
            entityManager.getTransaction().rollback();
        }
        finally {
            entityManager.close();
        }

        return rooms;
    }

    public boolean addRoom(Room send){

        EntityManager entityManager = getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            entityManager.persist(send);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            entityManager.getTransaction().rollback();
            System.out.println(ex.getMessage());
            return false;
        }
        finally {
            entityManager.close();
        }

        return true;
    }

    public boolean removeRoom(int roomID){

        EntityManager entityManager =getEntityManagerFactory().createEntityManager();
        entityManager.getTransaction().begin();

        try{
            Room toRemove = entityManager.find(Room.class,roomID);
            entityManager.remove(toRemove);
            entityManager.getTransaction().commit();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            entityManager.getTransaction().rollback();
            return false;
        }
        finally {
            entityManager.close();
        }

        return true;
    }
    //endregion
}
