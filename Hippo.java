public class Hippo extends Pachyderm{

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
