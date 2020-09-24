import java.util.Random;

// NOTE: For all random chance events below, the int variable chance will be between 1 and 100 inclusive.
public class Pachyderm extends Animal{

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