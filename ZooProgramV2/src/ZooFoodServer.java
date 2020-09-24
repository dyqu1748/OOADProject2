import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ZooFoodServer extends ZooEmployee {
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