//Source for combining 2 arraylists: https://www.geeksforgeeks.org/join-two-arraylists-in-java/#:~:text=Approach%3A%20ArrayLists%20can%20be%20joined,end%20of%20the%20first%20ArrayList.
//Sources for file writing:https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
//https://www.w3schools.com/java/java_files_create.asp
//https://www.tutorialspoint.com/java/lang/system_setout.htm
//https://www.iitk.ac.in/esc101/05Aug/tutorial/essential/threads/timer.html
//https://www.journaldev.com/1050/java-timer-timertask-example
//https://kodejava.org/how-do-i-listen-for-beans-property-change-event/

import java.beans.PropertyChangeSupport;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;


public class Main {
    protected static Main taskRunning;

    public static void main(String[] args){
        try{
            //This object will be used to make the main thread wait for the timer tasks to complete its one week run
            taskRunning = new Main();
            //Create output file to record all the actions and announcements
            FileOutputStream outFile = new FileOutputStream("dayatthezoo.out",false);

            //All print statements will be redirected and written to the output file.
            System.setOut(new PrintStream(outFile));

            //The director will issue out orders to the employees based on the current time
            Director bigBoss = new Director();

            //Create a ZooAnnouncer,Zookeeper,and ZooFoodServer for the zoo.
            ZooAnnouncer za = new ZooAnnouncer("Bob",18);
            Zookeeper zk = new Zookeeper("Bill",20,za);
            ZooFoodServer zfs = new ZooFoodServer("Jean", 15,za);

            //Add the employees above to the Director's attributes. This allows the Director to send them orders at the right times.
            bigBoss.setZooAnnouncer(za);
            bigBoss.setZooFoodServer(zfs);
            bigBoss.setZookeeper(zk);

            //Populate the zoo with 2 of each animal type
            bigBoss.setAnimals();

            //Initialize the zoo clock
            Timer ZooClock = new Timer();

            //hourlyTasks will notify the director of the day and time.
            HourIncrement hourlyTasks = new HourIncrement();

            //Add the director as an observer of the clock
            hourlyTasks.addPropertyChangeListener(bigBoss);

            //Have the clock notify the director that its hour has changed. Every hour in the zoo = .5 real-life seconds.
            ZooClock.scheduleAtFixedRate(hourlyTasks, 0, 500);
            synchronized (taskRunning){
                //Make the main thread wait for timer task to finish
                taskRunning.wait();

                //Week has passed in the timer task, so stop the timer
                ZooClock.cancel();
            }
            outFile.close();
        } catch (IOException | InterruptedException err){
            err.printStackTrace();
        }
    }
}

class HourIncrement extends TimerTask {
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
