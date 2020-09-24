import java.util.Random;

// NOTE: For all random chance events below, the int variable chance will be between 1 and 100 inclusive.
public class Feline extends Animal{

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
