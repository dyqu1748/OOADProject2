//Source for combining 2 arraylists: https://www.geeksforgeeks.org/join-two-arraylists-in-java/#:~:text=Approach%3A%20ArrayLists%20can%20be%20joined,end%20of%20the%20first%20ArrayList.
//Sources for file writing:https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
//https://www.w3schools.com/java/java_files_create.asp
//https://www.geeksforgeeks.org/redirecting-system-out-println-output-to-a-file-in-java/
import java.util.ArrayList;
import java.io.IOException;
import java.io.*;


public class Main {


    public static void main(String[] args){

        try{
            PrintStream o = new PrintStream(new File("tester.txt"));
            //Writes all the outputs to the named file above.
            System.setOut(o);
            Zoo AnnasZoo = new Zoo();

            //Populate the zoo with 2 of each animal type.
            AnnasZoo.setAnimals();

            //Create a ZooAnnouncer to observe other employees
            ZooAnnouncer za = new ZooAnnouncer("Bob",15);

            //Create a ZooFoodServer
            ZooFoodServer zfs = new ZooFoodServer("Gordon", 18,za);

            //Create a Zookeeper
            Zookeeper zk = new Zookeeper("Caral", 20,za);

            //While with our current program, the animal list will never change. This line here is to reduce the number of calls to getAnimals.
            //If you were to implement a method that causes the zoo to gain/lose animals, you'll want to move this line down to line 31 so that the methods below are correctly called on the right set of animals.
            ArrayList<Animal> curAnimals = AnnasZoo.getAllAnimals();

            //for a period of 7 days
            while(AnnasZoo.getDay() < 8){
                int curDay = AnnasZoo.getDay();
                //zookeeper must clock in,exercise, tell animals to sleep, clock out
                System.out.println(za.clockIn() + curDay + ".");
                System.out.println(zfs.clockIn() + curDay + ".");
                System.out.println(zk.clockIn() + curDay + ".");



                //zookeeper wakes the animals & Animals Wake up
                zk.wakeAnimals(curAnimals);

                //zookeeper roll calls the animals
                zk.rollCall(curAnimals);

                //zookeeper feeds animals
                zk.feed(curAnimals);

                //exercise animals
                zk.exercise(curAnimals);

                //tell animals to sleep
                zk.putToSleep(curAnimals);

                //zookeeper clocks out
                System.out.println(zk.clockOut() + curDay + ".\n");

                AnnasZoo.newDay();
            }
            o.close();
        } catch (IOException errC){
            errC.printStackTrace();
        }
    }
}
