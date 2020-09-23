//Sources: https://www.geeksforgeeks.org/generating-random-numbers-in-java/
// https://stackoverflow.com/questions/52301869/count-the-number-of-objects-created-by-java
// https://www.javatpoint.com/string-concatenation-in-java
// https://www.w3schools.com/java/java_constructors.asp

import java.util.Random;

//Abstract class for all types of animals. Has the attributes and methods expected of all animals as well as getters/setters for said attributes.
public abstract class Animal{

    //The animal's name, type, and identifier will all be private (encapsulation), as these attributes should not be easily changeable after the animal is created(save its name).

    private String name;
    private String aniType;
    //Every animal will have an id attribute to uniquely identify them. It will correlate to when they created with respect to the other animals.
    //For the sake of length and simplicity, each animal has their animal type as a prefix in their id to distinguish them from the employee id.
    //This can be a problem in the future if we add more animals, as there are some animal types that have very long names (i.e. mountain goat, mountain cur, etc.).
    private String aniID;

    private NoiseBehavior noiseBehavior;

    //Methods below provide abstraction for the tasks required by the zookeeper (eat, sleep, wake up, etc.)
    public void wakeUp(){
        System.out.println(this.name + " the " + this.aniType + " wakes up.");
    }

    //This method is the default for every animal in case we forget to override it in the subclasses.
//    public void makeNoise(){
//        System.out.println(this.name  + " the " + this.aniType + " makes a noise.");
//    }

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

    public void performNoise(){
        System.out.println(this.name + " the " + this.aniType + noiseBehavior.makeNoise());
    }

}

interface NoiseBehavior {
    public String makeNoise();
}

class PurrSound implements NoiseBehavior{
    public String makeNoise(){
        return(" purrs.");
    }
}

class WoofSound implements NoiseBehavior{
    public String makeNoise(){
        return(" woofs.");
    }
}

class BellowSound implements  NoiseBehavior{
    public String makeNoise(){
        return(" bellows.");
    }
}

class GroanSound implements  NoiseBehavior{
    public String makeNoise(){
        return(" groans.");
    }
}


// NOTE: For all random chance events below, the int variable chance will be between 1 and 100 inclusive.

class Feline extends Animal{

    //Unique version of sleep for felines (polymorphism). Implements the random chance aspect required by instructions.
    public void sleep(){
        Random rand = new Random();
        //Generates random number from 1-100
        int chance = rand.nextInt(100) + 1;
        //If chance is between 1 and 30 (30% chance), the feline will roam.
        if (chance <= 30){
            this.roam();
        }
        //If chance is between 31 and 60 (30% chance), the feline will make noise.
        else if (chance > 30 && chance <=60){
            this.performNoise();
        }
        //Otherwise, the feline will sleep (40% chance).
        else{
            String aniName = this.getName();
            String aniType = this.getAniType();
            System.out.println(aniName + " the " + aniType + " reluctantly goes to sleeps.");
        }
    }

}

//NOTE: Only create 2 of each type of animal. If you create more than 2, you will get an out-of-bounds error for the name setting. If you want to make more, append more names to the end of the nameChoice list.
//Every subclass will have a String array nameChoice containing the available names for that type of animal. The zoo class should be mindful of the number of names available when creating animals as to avoid an out-out-bounds error.
//numAni is present in every subclass. It assigns the corresponding name in nameChoice to how many animals of that type have been created.

class Tiger extends Feline{

    private static int numAni = 0;
    private String[] nameChoice = {"Tina", "Tony"};


    public Tiger(){
        //Assign name according to the number of lions created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new PurrSound());
        this.setAniType("Tiger");
        numAni++;
        this.setAniID(numAni);
    }

    public void eat(){
        String aniName = this.getName();
        String aniType = this.getAniType();
        System.out.println(aniName + " the " + aniType + " mauls down on its meal.");
    }

}

class Cat extends Feline{

    private static int numAni = 0;
    private String[] nameChoice = {"Charlie", "Carly"};

    public Cat(){
        //Assign name according to the number of cats created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new PurrSound());
        this.setAniType("Cat");
        numAni++;
        this.setAniID(numAni);
    }

}

class Lion extends Feline{

    private static int numAni = 0;
    String[] nameChoice = {"Leo", "Lily"};

    public Lion(){
        //Assign name according to the number of lions created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new PurrSound());
        this.setAniType("Lion");
        numAni++;
        this.setAniID(numAni);
    }
}

class Pachyderm extends Animal{

    //Unique version of roam for Pachyderms (polymorphism). Implements the random chance aspect required.
    public void roam(){
        Random rand = new Random();
        String aniName = this.getName();
        String aniType = this.getAniType();
        //Chance will be a number between 1 and 100
        int chance = rand.nextInt(100) + 1;
        //25% chance that the pachyderm will charge
        if (chance <= 25){
            System.out.println(aniName + " the " +  aniType + " charges.");
        }
        //75% chance the method works as originally intended.
        else{
            System.out.println(aniName + " the " +  aniType + " roams.");
        }
    }
}


class Rhino extends Pachyderm{

    private static int numAni = 0;
    String[] nameChoice = {"Robbie", "Ryan"};

    public Rhino(){
        //Assign name according to the number of rhinos created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new GroanSound());
        this.setAniType("Rhino");
        numAni++;
        this.setAniID(numAni);
    }

}

class Hippo extends Pachyderm{

    private static int numAni = 0;
    String[] nameChoice = {"Harry", "Hannah"};

    public Hippo(){
        //Assign name according to the number of hippos created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new GroanSound());
        this.setAniType("Hippo");
        numAni++;
        this.setAniID(numAni);
    }

}

class Elephant extends Pachyderm{

    private static int numAni = 0;
    String[] nameChoice = {"Ellie", "Evan"};

    public Elephant(){
        //Assign name according to the number of elephants created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new GroanSound());
        this.setAniType("Elephant");
        numAni++;
        this.setAniID(numAni);
    }

}

class Canine extends Animal{

    //Unique version of roam() method for Canines (polymorphism). All subtypes will chase their own tail (dog has chance of doing different action).
    public void roam(){
        String aniName = this.getName();
        String aniType = this.getAniType();
        System.out.println(aniName + " the " + aniType + " chases its own tail.");
    }
}

class Dog extends Canine{

    private static int numAni = 0;
    String[] nameChoice = {"Doug", "Dina"};

    public Dog(){
        //Assign name according to the number of dogs created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new WoofSound());
        this.setAniType("Dog");
        numAni++;
        this.setAniID(numAni);
    }

    //Unique version of roam() for dogs. Implements random chance aspect noted in requirements.
    public void roam(){
        Random rand = new Random();
        String aniName = this.getName();
        String aniType = this.getAniType();
        int chance = rand.nextInt(100) + 1;
        //25% chance the dog will dig.
        if (chance <= 25){
            System.out.println(aniName + " the " + aniType + " digs.");
        }
        //75% chance the dog will roam normally.
        else{
            System.out.println(aniName + " the " + aniType + " chases its own tail.");

        }
    }

}

class Wolf extends Canine{

    private static int numAni = 0;
    String[] nameChoice = {"Will", "Wyatt"};

    public Wolf(){
        //Assign name according to the number of wolves created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new WoofSound());
        this.setAniType("Wolf");
        numAni++;
        this.setAniID(numAni);
    }

}

class Cervidae extends Animal{

    public void eat(){
        String aniName = this.getName();
        String aniType = this.getAniType();
        System.out.println(aniName + " the " + aniType + " slowly chews its food.");
    }

}

class Moose extends Cervidae{

    private static int numAni = 0;
    String[] nameChoice = {"Manny", "Miles"};

    public Moose(){
        //Assign name according to the number of mooses created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new BellowSound());
        this.setAniType("Moose");
        numAni++;
        this.setAniID(numAni);
    }
}

class Caribou extends Cervidae{

    private static int numAni = 0;
    String[] nameChoice = {"Carl", "Carole"};

    public Caribou(){
        //Assign name according to the number of caribou created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new BellowSound());
        this.setAniType("Caribou");
        numAni++;
        this.setAniID(numAni);
    }
}
