package pl.levil.view;


import com.vaadin.annotations.Theme;

import com.vaadin.ui.*;
import pl.levil.MyUI;
import pl.levil.model.Months;
import pl.levil.model.Reservation;
import pl.levil.model.Room;

import pl.levil.resources.ResourceManager;
import pl.levil.util.NotificationManager;
import pl.levil.util.Updateable;
import pl.levil.view.components.calendar.CalendarFiled;
import pl.levil.view.components.calendar.ReservationDetailsWindow;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by ${levil} on 2017-03-31.
 */

//TODO: dodac obsluge lat przestepnych

@Theme("mytheme")
public class CalendarComponent extends CustomComponent implements Updateable{

    //region fields
    private Calendar calendar = Calendar.getInstance();
    private Months currentMonth;
    List<CalendarFiled> toFill;
    private List<Room> rooms;
    //endregion

    //region components
    private VerticalLayout panel;
    private HorizontalLayout controllLayout;
    private GridLayout calendarGrid = new GridLayout();
    private Label monthInfo = new Label();
    private Button addNewReservations = new Button();
    //endregion

    public CalendarComponent() {
        NotificationManager.getInstance().subscribeToGuiUpdate(this);

        currentMonth = Months.values()[LocalDate.now().getMonth().getValue()-1];
        calendar.set(LocalDate.now().getYear(), currentMonth.getIndex(), 1);

        panel = new VerticalLayout();
        controllLayout = new HorizontalLayout();

        addNewReservations = new Button();
        addNewReservations.setCaption("dodaj spotkanie");
        addNewReservations.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent clickEvent) {
                ReservationDetailsWindow window = new ReservationDetailsWindow();
                MyUI.getCurrent().getUI().addWindow(window);
            }
        });
        controllLayout.addComponent(addNewReservations);
        panel.addComponent(controllLayout);

        monthInfo.setPrimaryStyleName("label-month-name");
        panel.addComponent(monthInfo);

        panel.addComponent(calendarGrid);
        makeCalendarGrid(currentMonth);

        setCompositionRoot(panel);

    }

    private void makeCalendarGrid(Months month) {
        monthInfo.setValue(month.name());

        GridLayout newCalendarGrid = new GridLayout(7, 6);
        toFill = new ArrayList<>();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int row = 0;
        for (int d = 1; d <= month.getDays(); d++) {
            LocalDateTime dateTime = LocalDateTime.of(calendar.get(Calendar.YEAR),currentMonth.getIndex(),d,0,0,0);
            CalendarFiled field = new CalendarFiled(String.valueOf(d), dateTime);
            newCalendarGrid.addComponent(field,dayOfWeek,row);
            toFill.add(field);
            dayOfWeek++;
            if (dayOfWeek > 6) {
                dayOfWeek = 0;
                row++;
            }
        }

        panel.replaceComponent(calendarGrid,newCalendarGrid);

//        CompletableFuture.supplyAsync(()-> ResourceManager.getDataProvaider().getAllReservations(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH))).
//                thenAccept(this::applayReservations).exceptionally(e->{
//                    System.out.println(e.toString());
//         return null;
//        });
        applayReservations(ResourceManager.getDataProvaider().getAllReservations(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)));

    }

    public void changeMonth(String month) {
        if (month == "next") {
            if (currentMonth.getIndex() == 12) {
                currentMonth = Months.Styczeń;
                calendar.set(calendar.get(Calendar.YEAR) + 1, currentMonth.getIndex(), 1);
            } else
                currentMonth = Months.values()[currentMonth.getIndex() + 1];

            makeCalendarGrid(currentMonth);
        } else {
            if (currentMonth.getIndex() == 1) {
                currentMonth = Months.Grudzień;
                calendar.set(calendar.get(Calendar.YEAR + 1), currentMonth.getIndex(), 1);
            } else
                currentMonth = Months.values()[currentMonth.getIndex() - 1];

            makeCalendarGrid(currentMonth);
            UI.getCurrent().push();
        }
    }

    private void applayReservations(List<Reservation> reservations){
        for(Reservation res:reservations){
            for(CalendarFiled ff:toFill){
                if(res.getDate().getYear() == calendar.get(Calendar.YEAR) && res.getDate().getMonth().getValue() == calendar.get(Calendar.MONTH) && res.getDate().getDayOfMonth() == ff.getDate().getDayOfMonth()){
                    ff.getReservations().add(res);
                }
            }

        }
        UI.getCurrent().push();
    }

    @Override
    public void changesWereMade() {
        makeCalendarGrid(currentMonth);
        UI.getCurrent().push();
    }
}
