//https://kodejava.org/how-do-i-listen-for-beans-property-change-event/
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

//Implement observer pattern for ZooEmployee in the form of ZooAnnouncer. ZooAnnouncer will announce that an employee is doing a task if it has received an indication from that employee.
public class ZooAnnouncer extends ZooEmployee implements PropertyChangeListener {

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
