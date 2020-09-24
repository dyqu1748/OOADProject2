public class Cat extends Feline{

    private static int numAni = 0;
    private String[] nameChoice = {"Charlie", "Carly"};

    public Cat(){
        //Assign name according to the number of cats created (max 2).
        this.setName(nameChoice[numAni]);
        this.setNoiseBehavior(new PurrSound());
        this.setAniType("Cat");
        numAni++;
        this.setAniID(numAni);
    }

}
