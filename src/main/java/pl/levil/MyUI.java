package pl.levil;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.*;
import pl.levil.resources.StaticResources;
import pl.levil.view.CalendarComponent;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Push
public class MyUI extends UI {
    CalendarComponent calendar;
    @Override
    protected void init(VaadinRequest vaadinRequest) {
        //StaticResources.populateDB();

        calendar = new CalendarComponent();
       // calendar.setWidth("1200px");
        Button nextMonth = new Button(">");
        nextMonth.addClickListener(e -> calendar.changeMonth("next"));
        Button prevMonth = new Button("<");
        prevMonth.addClickListener(e -> calendar.changeMonth("prev"));

        HorizontalLayout layout = new HorizontalLayout();

        layout.addComponent(prevMonth);
        layout.addComponent(calendar);
        layout.addComponent(nextMonth);
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
