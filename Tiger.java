public class Tiger extends Feline{

    private static int numAni = 0;
    private String[] nameChoice = {"Tina", "Tony"};


    public Tiger(){
        //Assign name according to the number of lions created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new PurrSound());
        this.setAniType("Tiger");
        numAni++;
        this.setAniID(numAni);
    }

    public void eat(){
        String aniName = this.getName();
        String aniType = this.getAniType();
        System.out.println(aniName + " the " + aniType + " mauls down on its meal.");
    }

}
