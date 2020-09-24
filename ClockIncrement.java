//https://www.iitk.ac.in/esc101/05Aug/tutorial/essential/threads/timer.html
//https://www.journaldev.com/1050/java-timer-timertask-example

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.TimerTask;

public class ClockIncrement extends TimerTask {
    private PropertyChangeSupport watcher = new PropertyChangeSupport(this);
    private int time = 7;
    private int day = 0;

    public void run() {
        if (day == 0) {
            watcher.firePropertyChange("day", day, ++day);
        }
        watcher.firePropertyChange("time", time, ++time);
        //When the clock reaches 8 PM, increase the day by 1 and reset the time back to 7 AM.
        if (time == 20) {
            watcher.firePropertyChange("day", day, ++day);
            time = 7;
        }
        //If a week has passed, notify the main function that the timer task is finished and end the program.
        if (day > 7) {
            synchronized (Main.taskRunning){
                Main.taskRunning.notify();
            }
        }
    }

    //Add or remove the observer for the clock
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        watcher.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        watcher.removePropertyChangeListener(listener);
    }

    //Getters for the private attributes above. Time/day will only be modifiable through the running task above.
    public int getDay(){
        return day;
    }

    public int getTime(){
        return time;
    }
}
