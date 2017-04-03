package pl.levil.view.components.calendar;

import com.vaadin.ui.*;
import pl.levil.model.Reservation;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    private ListSelect reservationsList = new ListSelect();

    public CalendarFiled(String info, LocalDateTime date) {
        this.date = date;

        dayNumber.setCaption(info);
        dayNumber.setHeight("50px");
        dayOfWeek.setCaption(date.getDayOfWeek().toString());
        dayOfWeek.setHeight("50px");
        layout.addComponent(dayNumber);
        layout.addComponent(dayOfWeek);

        reservationsList.setSizeFull();
        reservationsList.setData(reservations);

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

    public  void addEvents(Reservation reservation){
        VerticalLayout newLayout = new VerticalLayout();
        for(int i=0;i<=layout.getComponentCount();i++){
            newLayout.addComponent(layout.getComponent(i));
        }
        CalendarEventButton but =  new CalendarEventButton(reservation.getName());
            but.addClickListener(e-> new ResvationDetailsWindow(
                    ((CalendarEventButton)e.getButton()).reservation.getName(),((CalendarEventButton)e.getButton()).reservation
            ));
        newLayout.addComponent(but);
        super.setCompositionRoot(newLayout);
        layout = newLayout;
        UI.getCurrent().push();
    }

    public LocalDateTime getDate() {
        return date;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}
