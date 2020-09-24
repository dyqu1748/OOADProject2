public class Moose extends Cervidae{

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