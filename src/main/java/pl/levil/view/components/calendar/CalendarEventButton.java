package pl.levil.view.components.calendar;

import com.vaadin.ui.Button;
import pl.levil.model.Reservation;

/**
 * Created by ${levil} on 2017-03-31.
 */
public class CalendarEventButton extends Button {
    public Reservation reservation;

    public CalendarEventButton(String caption) {
        super(caption);
    }
}
