//Sources: https://www.geeksforgeeks.org/generating-random-numbers-in-java/
// https://stackoverflow.com/questions/52301869/count-the-number-of-objects-created-by-java
// https://www.javatpoint.com/string-concatenation-in-java
// https://www.w3schools.com/java/java_constructors.asp
//http://best-practice-software-engineering.ifs.tuwien.ac.at/patterns/delegation.html


//Abstract class for all types of animals. Has the attributes and methods expected of all animals as well as getters/setters for said attributes.
public abstract class Animal{

    //The animal's name, type, and identifier will all be private (encapsulation), as these attributes should not be easily changeable after the animal is created(save its name).

    private String name;
    private String aniType;
    //Every animal will have an id attribute to uniquely identify them. It will correlate to when they created with respect to the other animals.
    //For the sake of length and simplicity, each animal has their animal type as a prefix in their id to distinguish them from the employee id.
    //This can be a problem in the future if we add more animals, as there are some animal types that have very long names (i.e. mountain goat, mountain cur, etc.).
    private String aniID;

    //All animals will reference the NoiseBehavior class below as all animal noises have been delegated to that class.
    private NoiseBehavior noiseBehavior;

    //Methods below provide abstraction for the tasks required by the zookeeper (eat, sleep, wake up, etc.)
    public void wakeUp(){
        System.out.println(this.name + " the " + this.aniType + " wakes up.");
    }

    public void eat(){
        System.out.println(this.name  + " the " + this.aniType + " eats.");
    }

    public void roam(){
        System.out.println(this.name  + " the " + this.aniType + " roams.");
    }

    public void sleep(){
        System.out.println(this.name  + " the " + this.aniType + " goes to sleep.");
    }


    //Getter and setter functions for the private name and animal-type attributes of the object (encapsulation).
    //Provides further abstraction for editing attributes of any animal.
    public String getName(){
        return name;
    }

    public void setName(String newName){
        name = newName;
    }

    public String getAniType(){
        return aniType;
    }

    public void setAniType(String newAniType){
        aniType = newAniType;
    }

    public void setAniID(int aniCount){
        aniID = aniType + aniCount;
    }

    public String getAniID(){
        return aniID;
    }

    public void setNoiseBehavior(NoiseBehavior nb){
        noiseBehavior = nb;
    }

    //Method that will be used to have the animal make noise, referencing the makeNoise method within NoiseBehavior.
    public void performNoise(){
        System.out.println(this.name + " the " + this.aniType + noiseBehavior.makeNoise());
    }

}

//NOTE: Only create 2 of each type of animal. If you create more than 2, you will get an out-of-bounds error for the name setting. If you want to make more, append more names to the end of the nameChoice list.
//Every subclass will have a String array nameChoice containing the available names for that type of animal. The zoo class should be mindful of the number of names available when creating animals as to avoid an out-out-bounds error.
//numAni is present in every subclass. It assigns the corresponding name in nameChoice to how many animals of that type have been created.

