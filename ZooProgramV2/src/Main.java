//Source for combining 2 arraylists: https://www.geeksforgeeks.org/join-two-arraylists-in-java/#:~:text=Approach%3A%20ArrayLists%20can%20be%20joined,end%20of%20the%20first%20ArrayList.
//Sources for file writing:https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
//https://www.w3schools.com/java/java_files_create.asp
//https://www.tutorialspoint.com/java/lang/system_setout.htm
//https://www.iitk.ac.in/esc101/05Aug/tutorial/essential/threads/timer.html
//https://www.journaldev.com/1050/java-timer-timertask-example
//https://kodejava.org/how-do-i-listen-for-beans-property-change-event/

import java.io.*;
import java.util.Timer;

public class Main {
    protected static Main taskRunning;

    public static void main(String[] args){
        try{
            //Let the user know that the program is currently running (the runtime is about 8.4 seconds total)
            System.out.println("The Zoo is now open! Give it about 10 seconds to run for 1 week...");

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

            //Initialize the ZooClock object which will be a timer
            Timer ZooClock = new Timer();

            //hourlyTasks will advance the clock by an hour and notify the director of the day and time.
            ClockIncrement hourlyTasks = new ClockIncrement();

            //Add the director as an observer of the clock
            hourlyTasks.addPropertyChangeListener(bigBoss);

            //Have the clock notify the director that its hour has changed. Every hour in the zoo = .1 real-life seconds.
            ZooClock.scheduleAtFixedRate(hourlyTasks, 0, 100);
            synchronized (taskRunning){
                //Make the main thread wait for timer task to finish
                taskRunning.wait();

                //Week has passed in the timer task, so stop the timer
                ZooClock.cancel();
            }
            outFile.close();
            //Reset output to print to console.
            //https://makeinjava.com/write-console-output-file-reset-standard-output-java/
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            System.out.println("Done!");
        } catch (IOException | InterruptedException err){
            err.printStackTrace();
        }
    }
}

