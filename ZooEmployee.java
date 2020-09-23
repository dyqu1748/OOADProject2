//Source: https://www.w3schools.com/java/java_arraylist.asp
//https://kodejava.org/how-do-i-listen-for-beans-property-change-event/
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
    //Attribute added to properly implement the observer pattern below. Holds the current action of the employee which will be used in output.
    private String curAction = "";

    //clockIn and clockOut will be utilized to notify the user that the zookeeper has arrived on day X. They should be used in conjunction with a string indicating the day in main.

    public String clockIn(){
        return(this.role + " arrives at Zoo on day ");
    }

    public String clockOut(){
        return(this.role + " goes home at the end of day ");
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

    public void setCurAction(String action){
        curAction = action;
    }

    public String getCurAction(){
        return curAction;
    }

    //Employee IDs will have the employee's role appended with the number in which they were created for that role.
    public void setEmpID(){
        empCount++;
        empID = role + empCount;
    }

    public String getEmpID(){
        return empID;
    }


}

//Implement observer pattern for ZooEmployee in the form of ZooAnnouncer. ZooAnnouncer will announce that an employee is doing a task if it has received an indication from that employee.
class ZooAnnouncer extends ZooEmployee implements PropertyChangeListener{

    public ZooAnnouncer(String name, float wage){
        this.setName(name);
        this.setWage(wage);
        this.setRole("ZooAnnouncer");
        this.setEmpID();

    }
    public void propertyChange(PropertyChangeEvent evt){
        //actInfo hold the information on who is performing what action. The property name will have the action while the source will be the role of the employee performing said action.
        String actInfo = evt.toString();
        //startRole and endRole will get the indices of where the employee's role title starts and ends.
        int startRole = actInfo.indexOf("source=") + 7;
        int endRole = actInfo.indexOf('@');
        String role = actInfo.substring(startRole,endRole);
        //Print out who is performing what action
        String action = "Hi, this is the Zoo Announcer. The " + role + " is about to " + evt.getNewValue() + "\n";
        System.out.println(action);
    }

}

class ZooFoodServer extends ZooEmployee{
    //Watcher will be the ZooAnnouncer that observes and announces when the ZooFoodServer serves food.
    private PropertyChangeSupport watcher = new PropertyChangeSupport(this);
    //Used to keep track if ZFS has already served lunch or not.
    private boolean lunchServed = false;

    public ZooFoodServer(String name, float wage, ZooAnnouncer za){
        this.setName(name);
        this.setWage(wage);
        this.setRole("ZooFoodServer");
        this.setEmpID();
        this.addPropertyChangeListener(za);
    }

    public void makeFood(){
        //Print out that the server is making food
        System.out.println(this.getRole() + " is making food.\n");
        this.setCurAction("making food");
    }

    public void serveFood(){
        String meal = "";
        if (this.lunchServed == false){
            //Set lunchServed to true so next time this is called it will serve dinner instead
            this.lunchServed = true;
            //Notify ZooAnnouncer (observer) that it is about to serve lunch
            String pastAct = this.getCurAction();
            this.setCurAction("serve lunch to the animals!");
            watcher.firePropertyChange("curAction",pastAct,this.getCurAction());
            meal = "lunch";
        }
        else{
            //Set lunchServed to true so next time this is called it will serve dinner instead
            this.lunchServed = false;
            //Notify ZooAnnouncer (observer) that it is about to serve dinner
            String pastAct = this.getCurAction();
            this.setCurAction("serve dinner to the animals!");
            watcher.firePropertyChange("curAction",pastAct,this.getCurAction());
            meal = "dinner";
        }
        //Print out that the server is serving food
        System.out.println("The " + this.getRole() + " is serving " + meal + ".\n");

    }

    public void cleanFood(){
        //Print out that they are cleaning up the food
        System.out.println(this.getRole() + " is cleaning up the food.\n");
        this.setCurAction("cleaning up the food");
    }

    //Add or remove the observer for the employee
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        watcher.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        watcher.removePropertyChangeListener(listener);
    }
}

class Zookeeper extends ZooEmployee{
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
