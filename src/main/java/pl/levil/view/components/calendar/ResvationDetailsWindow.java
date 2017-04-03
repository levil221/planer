package pl.levil.view.components.calendar;

import com.vaadin.ui.*;
import pl.levil.model.Reservation;
import pl.levil.model.Room;

import java.time.LocalDateTime;

/**
 * Created by ${levil} on 2017-03-31.
 */
public class ResvationDetailsWindow extends Window {
    Reservation reservation;

    private VerticalLayout layout = new VerticalLayout();
    private TextField nameTextField = new TextField("Nazwa");
    private TextArea descriptionTextField= new TextArea("Opis");
    private TextField duration = new TextField("Czas");
    private ComboBox<Room> roomComboBox = new ComboBox<>("Pokój");
    private InlineDateTimeField dateField = new InlineDateTimeField("data spotkania");

    private Button saveChanges = new Button("Zapisz zmiany");
    private Button checkAvailability = new Button("Zapisz zmiany");


    public ResvationDetailsWindow( ) {
        super("Nowe spotkanie");
        reservation = new Reservation();
        compose();
    }

    public ResvationDetailsWindow(String caption, Reservation reservation) {
        super(caption);
        this.reservation = reservation;
        compose();
    }

    private void compose(){
        nameTextField.setValue(reservation.getName());
        layout.addComponent(nameTextField);
        descriptionTextField.setValue(reservation.getDescription());
        layout.addComponent(descriptionTextField);
        roomComboBox.setValue(reservation.getRoom());
        layout.addComponent(roomComboBox);
        LocalDateTime locale = LocalDateTime.now();
        if(reservation.getDate() != null)
            locale = LocalDateTime.parse(reservation.getDate().toString());

        dateField.setValue(locale);
        layout.addComponent(dateField);
        duration.setValue(String.valueOf(reservation.getDuration()));
        layout.addComponent(duration);

        this.setContent(layout);
    }
}
