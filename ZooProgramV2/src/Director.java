import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

//The Director will observe the clock (observer pattern) as well as issue orders out to the zoo employees at the appropriate times.
public class Director implements PropertyChangeListener{
    private Zookeeper zk;
    private ZooFoodServer zfs;
    private ZooAnnouncer za;
    private ArrayList<Animal> animals;
    private int curDay;

    public Director(){
        animals = new ArrayList<>();
        curDay = 0;
    }

    //Getter and setters for the employees of the zoo.
    public void setZookeeper(Zookeeper zooKeeper){
        zk = zooKeeper;
    }

    public Zookeeper getZookeeper(){
        return(zk);
    }

    public void setZooFoodServer(ZooFoodServer zooFoodServer){
        zfs = zooFoodServer;
    }

    public ZooFoodServer getZooFoodServer(){
        return(zfs);
    }

    public void setZooAnnouncer(ZooAnnouncer zooAnnouncer){
        za = zooAnnouncer;
    }

    public ZooAnnouncer getZooAnnouncer(){
        return(za);
    }

    public void propertyChange(PropertyChangeEvent evt){
        String timeDay = evt.getPropertyName();
        //If it is the beginning of the first day or the end of any other day, update the current day for proper output.
        if (timeDay.equals("day")){
            int day = (Integer) evt.getNewValue();
            curDay = day;
        }
        //If the time is received instead, perform the appropriate task(s).
        else if (timeDay.equals("time")){
            int time = (Integer) evt.getNewValue();
            //First, announce the current time.
            if (time <= 12){

                System.out.println("The current time is " + time + ":00 AM\n");
            }
            else{
                int aftTime= time -12;
                System.out.println("The current time is " + aftTime + ":00 PM\n");
            }
            switch (time){
                //At 8 AM, everyone clocks in to work.
                case 8:
                    System.out.println(za.clockIn() + curDay);
                    System.out.println(zk.clockIn() + curDay);
                    System.out.println(zfs.clockIn() + curDay + "\n");
                    break;
                case 9:
                    //Wake up animals at 9AM
                    zk.wakeAnimals(animals);
                    break;
                case 10:
                    //Roll call at 10AM
                    zk.rollCall(animals);
                    break;
                //At 11 AM and 4 PM, make food
                case 11:
                case 16:
                    zfs.makeFood();
                    break;
                //At 12 PM and 5 PM, serve the food and feed it to the animals.
                case 12:
                case 17:
                    zfs.serveFood();
                    zk.feed(animals);
                    break;
                //At 1 PM and 6PM, clean up the food.
                case 13:
                case 18:
                    zfs.cleanFood();
                    break;
                //At 2 PM, make the animals go exercise
                case 14:
                    zk.exercise(animals);
                    break;
                //At 7 PM, put the animals to sleep
                case 19:
                    zk.putToSleep(animals);
                    break;
                //At 8 PM, have everyone clock out for the day.
                case 20:
                    System.out.println(za.clockOut() + curDay);
                    System.out.println(zk.clockOut() + curDay);
                    System.out.println(zfs.clockOut() + curDay + "\n");
                    break;

            }

        }
    }

    //Populate the zoo with 2 of each kind of animal
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