public class Caribou extends Cervidae{

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
