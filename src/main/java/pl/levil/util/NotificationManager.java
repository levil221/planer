package pl.levil.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${levil} on 2017-04-03.
 */
public class NotificationManager {

    List<Updateable> updateables = new ArrayList<>();


    private static NotificationManager ourInstance = new NotificationManager();

    public static NotificationManager getInstance() {
        return ourInstance;
    }

    private NotificationManager() {

    }

    public void subscribeToGuiUpdate(Updateable up){
        updateables.add(up);
    }

    public void notifyGuiUpdateList(){
        for(Updateable up:updateables){
            up.changesWereMade();
        }
    }
}
