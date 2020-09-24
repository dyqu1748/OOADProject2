public class Rhino extends Pachyderm{

    private static int numAni = 0;
    String[] nameChoice = {"Robbie", "Ryan"};

    public Rhino(){
        //Assign name according to the number of rhinos created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new GroanSound());
        this.setAniType("Rhino");
        numAni++;
        this.setAniID(numAni);
    }

}