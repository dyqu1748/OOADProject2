//Source: https://www.w3schools.com/java/java_arraylist.asp

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

//Abstract class for all types of zoo employees. Has the attributes and methods expected of employee types as well as the getter/setter methods for said attributes.
public abstract class ZooEmployee{

    //Employee's name, wage, role, and id are private attributes (encapsulation). These values should not be easily accessible as their values do not often change (or at all in the case of name).
    private String name;
    private float wage;
    private String role;
    //Every employee will have an id attribute to identify them. It will correlate to when they created with respect to the other employees.
    private String empID;
    private static int empCount = 0;

    //clockIn and clockOut will be utilized to notify the user that the zookeeper has arrived on day X. They should be used in conjunction with a string indicating the day in main.

    public String clockIn(){
        return(this.role + " arrives at Zoo ");
    }

    public String clockOut(){
        return(this.role + " goes home ");
    }

    //Getter and setter functions for ZooEmployee's private attributes (abstraction).
    public void setName(String empName){
        name = empName;
    }

    public String getEmpName(){
        return name;
    }

    public void setWage(float curWage){
        wage = curWage;
    }

    public float getWage(){
        return wage;
    }

    public void setRole(String empRole){
        role = empRole;
    }

    public String getRole(){
        return role;
    }

    //Employee ID's will have EMP at the front to distinguish them from the animals' IDs.
    public void setEmpID(){
        empCount++;
        empID = "EMP" + empCount;
    }

    public String getEmpID(){
        return empID;
    }


}

class ZooAnnouncer extends ZooEmployee implements PropertyChangeListener{

    public ZooAnnouncer(String name, float wage){
        this.setName(name);
        this.setWage(wage);
        this.setRole("ZooAnnouncer");
        this.setEmpID();

    }
    public void propertyChange(PropertyChangeEvent evt){
        String actInfo = evt.toString();
        int startRole = actInfo.indexOf("source=") + 7;
        int endRole = actInfo.indexOf('@');
        String role = actInfo.substring(startRole,endRole);
        String action = "\nHi, this is the Zoo Announcer. The " + role + " is about to " + evt.getPropertyName() + " the animals!";
        System.out.println(action);
        //Need to somehow record announcement
    }

    public String recordAnnounce(String ann){
        return ann;
    }

}

class ZooFoodServer extends ZooEmployee{
    private PropertyChangeSupport watcher = new PropertyChangeSupport(this);
    private boolean lunchServed = false;

    public ZooFoodServer(String name, float wage, ZooAnnouncer za){
        this.setName(name);
        this.setWage(wage);
        this.setRole("Zoo food server");
        this.setEmpID();
        this.addPropertyChangeListener(za);
    }

    public void makeFood(){
        //Make food
        System.out.println(this.getRole() + "is making food");
    }

    public void serveFood(){
        //Serve at 12AM and 5PM
        String meal = "";
        if (this.lunchServed == false){
            this.lunchServed = true;
            watcher.firePropertyChange("serve lunch to",false,true);
            meal = "lunch";
        }
        else{
            this.lunchServed = false;
            watcher.firePropertyChange("serve dinner to",true,false);
            meal = "dinner";
        }
        System.out.println(this.getRole() + "is serving " + meal + ".");

    }

    public void cleanFood(){
        //After serving food
        System.out.println(this.getRole() + "is cleaning up the food.");
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        watcher.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        watcher.removePropertyChangeListener(listener);
    }
}

class Zookeeper extends ZooEmployee{
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
        watcher.firePropertyChange("wake",false,true);
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " wakes up " + aniName + " the " + aniType +".");
            zoo.get(i).wakeUp();
        }
    }

    public void rollCall(ArrayList<Animal> zoo){
        watcher.firePropertyChange("roll call",false,true);
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " calls out to " + aniName + " the " + aniType + ".");
            zoo.get(i).makeNoise();
        }
    }

    public void feed(ArrayList<Animal> zoo){
        watcher.firePropertyChange("feed",false,true);
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " feeds " + aniName + " the " + aniType + ".");
            zoo.get(i).eat();
        }
    }

    public void exercise(ArrayList<Animal> zoo){
        watcher.firePropertyChange("exercise with",false,true);
        int len = zoo.size();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " orders " + aniName + " the " + aniType + " to go roam.");
            zoo.get(i).roam();
        }
    }

    public void putToSleep(ArrayList<Animal> zoo){
        watcher.firePropertyChange("put to sleep all of",false,true);
        int len = zoo.size();
        ArrayList<String> actions = new ArrayList<>();
        for (int i = 0; i < len; i++){
            String aniName = zoo.get(i).getName();
            String aniType = zoo.get(i).getAniType();
            System.out.println(this.getRole() + " puts " + aniName + " the " + aniType + " to sleep.");
            zoo.get(i).sleep();
        }
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        watcher.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        watcher.removePropertyChangeListener(listener);
    }

}
