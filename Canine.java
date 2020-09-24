public class Canine extends Animal{

    //Unique version of roam() method for Canines (polymorphism). All subtypes will chase their own tail (dog has chance of doing different action).
    public void roam(){
        String aniName = this.getName();
        String aniType = this.getAniType();
        System.out.println(aniName + " the " + aniType + " chases its own tail.");
    }
}
