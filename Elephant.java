public class Elephant extends Pachyderm{

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