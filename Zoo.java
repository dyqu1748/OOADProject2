//Source: https://www.geeksforgeeks.org/arraylist-in-java/
import java.util.ArrayList;

//This class will hold all of the animals in an arraylist and keep track of the number of days the zoo has been in operation.
public class Zoo {

    private ArrayList<Animal> animals;
    private int day;

    public Zoo(){
        day = 1;
        animals = new ArrayList<>();
    }

    //Getter/Setters for our private attributes
    public int getDay(){
        return day;
    }

    public void newDay(){
        day++;
    }

    public ArrayList<Animal> getAllAnimals(){
        return animals;
    }

    //While never used, this method provides abstraction in case future iterations of this program need to find specific animals.
    public Animal getAnimal(String aniID){
        for (Animal curAni: animals){
            String curAniID = curAni.getAniID();
            if (curAniID.equals(aniID)){
                return curAni;
            }
        }
        System.out.println(aniID + " does not exist in the zoo.");
        return null;
    }

    //Method setAnimals() populate the Animals in the zoo. There are 2 of each type of animal in this zoo
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
