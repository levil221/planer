package pl.levil.view.components.calendar;

import com.vaadin.ui.*;
import pl.levil.MyUI;
import pl.levil.model.Reservation;
import pl.levil.model.Room;
import pl.levil.resources.ResourceManager;
import pl.levil.util.NotificationManager;

import java.time.LocalDateTime;

/**
 * Created by ${levil} on 2017-03-31.
 */
public class ReservationDetailsWindow extends Window {


    private Reservation reservation;

    private VerticalLayout layout = new VerticalLayout();
    private TextField nameTextField = new TextField("Nazwa");
    private TextArea descriptionTextField= new TextArea("Opis");
    private TextField duration = new TextField("Czas");
    private ComboBox<Room> roomComboBox = new ComboBox<>("Pokój");
    private InlineDateTimeField dateField = new InlineDateTimeField("data spotkania");
    private HorizontalLayout controlLayout = new HorizontalLayout();

    private Button saveChanges = new Button("Zapisz zmiany");
    private Button checkAvailability = new Button("Sprawdz dostępność");


    public ReservationDetailsWindow( ) {
        super("Nowe spotkanie");
        reservation = new Reservation();
        compose();
    }

    public ReservationDetailsWindow(String caption, Reservation reservation) {
        super(caption);
        this.reservation = reservation;
        compose();
        fillComponents();
    }
    private void fillComponents(){
        nameTextField.setValue(reservation.getName());
        descriptionTextField.setValue(reservation.getDescription());
        roomComboBox.setValue(reservation.getRoom());
        LocalDateTime locale = LocalDateTime.now();
        if(reservation.getDate() != null)
            locale = LocalDateTime.parse(reservation.getDate().toString());

        dateField.setValue(locale);
        duration.setValue(String.valueOf(reservation.getDuration()));
    }
    private void compose(){

        layout.addComponent(nameTextField);
        layout.addComponent(descriptionTextField);
        layout.addComponent(roomComboBox);

        layout.addComponent(dateField);

        layout.addComponent(duration);
        saveChanges.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                Reservation res = ((ReservationDetailsWindow)clickEvent.getButton().getParent().getParent().getParent()).getReservation();
                if(res.getId() == 0){
                   if(!ResourceManager.getDataProvaider().addReservation(res))
                       Notification.show("Nie udało się dodać nowego spotkania");
                   else
                       Notification.show("Dodano nowe spotkanie");

                }
                else
                    if(!ResourceManager.getDataProvaider().updateReservation(res.getId(),res))
                        Notification.show("Nie udało się uaktualnić wpisu o tym spotkaniu");
                    else
                        Notification.show("Uaktualniono wpis o spotkaniu");


                NotificationManager.getInstance().notifyGuiUpdateList();
            }
        });
        controlLayout.addComponent(saveChanges);
        controlLayout.addComponent(checkAvailability);

        layout.addComponent(controlLayout);

        this.setContent(layout);
    }
    public Reservation getReservation() {
        return reservation;
    }
}
