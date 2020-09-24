public class Cervidae extends Animal {

    public void eat(){
        String aniName = this.getName();
        String aniType = this.getAniType();
        System.out.println(aniName + " the " + aniType + " slowly chews its food.");
    }

}