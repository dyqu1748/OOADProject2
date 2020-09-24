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

