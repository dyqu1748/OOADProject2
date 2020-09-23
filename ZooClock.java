import java.util.Timer;
import java.util.TimerTask;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;


public class ZooClock {
    private PropertyChangeSupport watcher = new PropertyChangeSupport(this);
    Timer clock;

    public ZooClock(Director cw) {
        clock = new Timer();
        watcher.addPropertyChangeListener(cw);
        clock.scheduleAtFixedRate(new HourAnnounce(), 0,2000);
    }

    class HourAnnounce extends TimerTask {
        private int time = 7;
        private int day = 0;
        public void run() {
            if (day == 0){
                watcher.firePropertyChange("day",day,++day);
            }
            watcher.firePropertyChange("time",time,++time);
            if (time == 20) {
                watcher.firePropertyChange("day",day,++day);
                time=8;
            }
            if (day > 7){
                clock.cancel();
            }
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        watcher.removePropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        watcher.removePropertyChangeListener(listener);
    }

    public static void main(String args[]) {
        Director bigBoss = new Director();
        bigBoss.setAnimals();
        new ZooClock(bigBoss);
    }

}

class Director implements PropertyChangeListener{
    private Zookeeper zk;
    private ZooFoodServer zfs;
    private ZooAnnouncer za;
    private ArrayList<Animal> animals;
    private int curDay;

    public Director(){
        za = new ZooAnnouncer("Bob",18);
        zk = new Zookeeper("Bill",20,za);
        zfs = new ZooFoodServer("Jean", 15,za);
        animals = new ArrayList<>();
        curDay = 0;
    }

    public void propertyChange(PropertyChangeEvent evt){
        String timeDay = evt.getPropertyName();
        if (timeDay.equals("day")){
            int day = (Integer) evt.getNewValue();
            curDay = day;
        }
        if (timeDay.equals("time")){
            int time = (Integer) evt.getNewValue();
            if (time <= 12){

                System.out.println("\nThe current time is " + time + ":00 AM\n");
            }
            else{
                int aftTime= time -12;
                System.out.println("\nThe current time is " + aftTime + ":00 PM\n");
            }
            switch (time){
                case 8:
                    System.out.println(za.clockIn() + curDay);
                    System.out.println(zk.clockIn() + curDay);
                    System.out.println(zfs.clockIn() + curDay);
                    break;
                case 9:
                    //Wake up animals
                    zk.wakeAnimals(animals);
                    //Roll call
                    zk.rollCall(animals);
                    break;
                case 11:
                case 16:
                    //Prepare food
                    zfs.makeFood();
                    break;
                case 12:
                case 17:
                    //Serve food
                    zfs.serveFood();
                    zk.feed(animals);
                    break;
                case 13:
                case 18:
                    //Clean food
                    zfs.cleanFood();
                    break;
                case 14:
                    //Exercise animals
                    zk.exercise(animals);
                    break;
                case 19:
                    //Put animals to sleep
                    zk.putToSleep(animals);
                    break;
                case 20:
                    System.out.println(za.clockOut() + curDay);
                    System.out.println(zk.clockOut() + curDay);
                    System.out.println(zfs.clockOut() + curDay);
                    break;

            }

        }
    }

    public void setAnimals(){
        //add tigers
        Tiger T1 = new Tiger();
        Tiger T2 = new Tiger();
        animals.add(T1);
        animals.add(T2);

        //add cats
        Cat C1 = new Cat();
        Cat C2 = new Cat();
        animals.add(C1);
        animals.add(C2);

        //add lions
        Lion L1 = new Lion();
        Lion L2 = new Lion();
        animals.add(L1);
        animals.add(L2);

        //add Rhino
        Rhino R1 = new Rhino();
        Rhino R2 = new Rhino();
        animals.add(R1);
        animals.add(R2);

        //add Hippo
        Hippo H1 = new Hippo();
        Hippo H2 = new Hippo();
        animals.add(H1);
        animals.add(H2);

        //add Elephant
        Elephant E1 = new Elephant();
        Elephant E2 = new Elephant();
        animals.add(E1);
        animals.add(E2);

        //add Dog
        Dog D1 = new Dog();
        Dog D2 = new Dog();
        animals.add(D1);
        animals.add(D2);

        //add Wolf
        Wolf W1 = new Wolf();
        Wolf W2 = new Wolf();
        animals.add(W1);
        animals.add(W2);

        //add Moose
        Moose M1 = new Moose();
        Moose M2 = new Moose();
        animals.add(M1);
        animals.add(M2);

        //add Caribou
        Caribou C3 = new Caribou();
        Caribou C4 = new Caribou();
        animals.add(C3);
        animals.add(C4);
    }
}