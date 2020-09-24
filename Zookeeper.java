//Source: https://www.w3schools.com/java/java_arraylist.asp
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Zookeeper extends ZooEmployee{
    //Watcher will be the ZooAnnouncer that observes and announces when the ZooFoodServer serves food.
    private PropertyChangeSupport watcher = new PropertyChangeSupport(this);

    public Zookeeper(String name, float wage, ZooAnnouncer za){
        this.setName(name);
        this.setWage(wage);
        this.setRole("Zookeeper");
        this.setEmpID();
        this.addPropertyChangeListener(za);
    }

    //Methods below provide abstraction for the responsibilities expected of the zookeeper (feed animals, wake them up, put them to sleep, etc.)
    //The methods below will take in an arraylist of animals, iterate through them, and perform the appropriate action on them.
    //Each method will also announce that the zookeeper is performing said action on the current animal in the arraylist
    //The methods will return the actions of both the zookeeper and each animal in an arraylist.

    public void wakeAnimals(ArrayList<Animal> zoo){
        //Notify ZooAnnouncer (observer) that it is about to wake the animals
        String pastAct = this.getCurAction();
        this.setCurAction("wake up the animals!");
        watcher.firePropertyChange("curAction",pastAct,this.getCurAction());
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " wakes up " + aniName + " the " + aniType +".");
            zoo.get(i).wakeUp();
        }
        System.out.println();
    }

    public void rollCall(ArrayList<Animal> zoo){
        //Notify ZooAnnouncer (observer) that it is about to roll call the animals
        String pastAct = this.getCurAction();
        this.setCurAction("roll call the animals!");
        watcher.firePropertyChange("curAction",pastAct,this.getCurAction());
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " calls out to " + aniName + " the " + aniType + ".");
            zoo.get(i).performNoise();
        }
        System.out.println();
    }

    public void feed(ArrayList<Animal> zoo){
        //Notify ZooAnnouncer (observer) that it is about to feed the animals
        String pastAct = this.getCurAction();
        this.setCurAction("feed the animals!");
        watcher.firePropertyChange("curAction",pastAct,this.getCurAction());
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " feeds " + aniName + " the " + aniType + ".");
            zoo.get(i).eat();
        }
        System.out.println();
    }

    public void exercise(ArrayList<Animal> zoo){
        //Notify ZooAnnouncer (observer) that it is about to exercise with the animals
        String pastAct = this.getCurAction();
        this.setCurAction("exercise with the animals!");
        watcher.firePropertyChange("curAction",pastAct,this.getCurAction());
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " orders " + aniName + " the " + aniType + " to go roam.");
            zoo.get(i).roam();
        }
        System.out.println();
    }

    public void putToSleep(ArrayList<Animal> zoo){
        //Notify ZooAnnouncer (observer) that it is about to put all of the animals to sleep
        String pastAct = this.getCurAction();
        this.setCurAction("put the animals to sleep!");
        watcher.firePropertyChange("curAction",pastAct,this.getCurAction());
        int len = zoo.size();
        ArrayList<String> actions = new ArrayList<>();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " puts " + aniName + " the " + aniType + " to sleep.");
            zoo.get(i).sleep();
        }
        System.out.println();
    }

    //Add or remove the observer for the employee
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        watcher.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        watcher.removePropertyChangeListener(listener);
    }

}