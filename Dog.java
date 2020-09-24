import java.util.Random;

// NOTE: For all random chance events below, the int variable chance will be between 1 and 100 inclusive.
public class Dog extends Canine{

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