public class Lion extends Feline{

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