public class Wolf extends Canine{

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
