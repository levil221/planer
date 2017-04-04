package pl.levil.view.components.calendar;

import com.vaadin.data.provider.DataProvider;
import com.vaadin.event.selection.MultiSelectionEvent;
import com.vaadin.event.selection.MultiSelectionListener;
import com.vaadin.shared.Registration;
import com.vaadin.ui.*;
import pl.levil.MyUI;
import pl.levil.model.Reservation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${levil} on 2017-03-31.
 */
public class CalendarFiled extends CustomComponent {
    private LocalDateTime date;
    private List<Reservation> reservations = new ArrayList<>();
    private VerticalLayout layout = new VerticalLayout();
    private Label dayNumber = new Label();
    private Label dayOfWeek = new Label();
    private ListSelect<Reservation> reservationsList;

    public CalendarFiled(String info, LocalDateTime date) {
        this.date = date;

        dayNumber.setCaption(info);
        dayOfWeek.setCaption(date.getDayOfWeek().toString());
        layout.addComponent(dayNumber);
        layout.addComponent(dayOfWeek);

        reservationsList = new ListSelect("Spotkania:",reservations);
        reservationsList.setItemCaptionGenerator(Reservation::getName);
        reservationsList.setHeight("70px");
        reservationsList.setWidth("100px");
        reservationsList.addValueChangeListener( event->{
            Reservation res = (Reservation)event.getValue().toArray()[0];
    ReservationDetailsWindow window = new ReservationDetailsWindow(res.getName(),res);
            MyUI.getCurrent().getUI().addWindow(window);
});

        layout.addComponent(reservationsList);

        layout.setWidth("150px");
        layout.setHeight("220px");
        setCompositionRoot(layout);
        }

public VerticalLayout getLayout() {
        return layout;
        }

public void setLayout(VerticalLayout layout) {
        this.layout = layout;
        }

public Label getDayNumber() {
        return dayNumber;
        }

    public void setDayNumber(Label dayNumber) {
        this.dayNumber = dayNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
