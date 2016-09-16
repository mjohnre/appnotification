package appnotification;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author JohnReMugar
 */
public class NotificationTimer {
    
    public static Timer timer = new Timer("Notification");//create a new Timer;
    
    public NotificationTimer(int delayMillis, int intervalMillis) {
	TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
		new NotificationMessage(Constants.defaultMessageDurationMillis);
            }
        };

        getTimer().scheduleAtFixedRate(timerTask, delayMillis, intervalMillis);//this line starts the timer at the same time its executed
    }
    
    public static Timer getTimer() {
	return timer;
    }
    
    public static void cancel() {
	getTimer().cancel();
    }
}
