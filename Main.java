//Source for combining 2 arraylists: https://www.geeksforgeeks.org/join-two-arraylists-in-java/#:~:text=Approach%3A%20ArrayLists%20can%20be%20joined,end%20of%20the%20first%20ArrayList.
//Sources for file writing:https://stackoverflow.com/questions/6548157/how-to-write-an-arraylist-of-strings-into-a-text-file
//https://www.w3schools.com/java/java_files_create.asp
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;


public class Main {


    public static void main(String[] args){

        try{
            PrintStream o = new PrintStream(new File("tester.txt"));
            System.setOut(o);
            Zoo AnnasZoo = new Zoo();
            //create a variable to hold all the Animals from the Zoo
            AnnasZoo.setAnimals();

            ZooAnnouncer za = new ZooAnnouncer("Bob",15);

            //Create a Zookeeper
            Zookeeper z = new Zookeeper("Caral", 20,za);

            //While with our current program, the animal list will never change. This line here is to reduce the number of calls to getAnimals.
            //If you were to implement a method that causes the zoo to gain/lose animals, you'll want to move this line down to line 31 so that the methods below are correctly called on the right set of animals.
            ArrayList<Animal> curAnimals = AnnasZoo.getAllAnimals();

            //for a period of 7 days
            while(AnnasZoo.getDay() < 8){
                int curDay = AnnasZoo.getDay();
                //zookeeper must clock in,exercise, tell animals to sleep, clock out
                System.out.println(z.clockIn() + "on Day " + curDay + ".");


                //zookeeper wakes the animals & Animals Wake up
                z.wakeAnimals(curAnimals);

                //zookeeper roll calls the animals
                z.rollCall(curAnimals);

                //zookeeper feeds animals
                z.feed(curAnimals);

                //exercise animals
                z.exercise(curAnimals);

                //tell animals to sleep
                z.putToSleep(curAnimals);

                //zookeeper clocks out
                System.out.println(z.clockOut() + "at the end of Day " + curDay + ".\n");

                AnnasZoo.newDay();
            }
            o.close();
        } catch (IOException errW){
            errW.printStackTrace();
            return;
        }
        //Create the Zoo
//        Zoo AnnasZoo = new Zoo();
//        //create a variable to hold all the Animals from the Zoo
//        AnnasZoo.setAnimals();
//
//        ZooAnnouncer za = new ZooAnnouncer("Bob",15);
//
//        //Create a Zookeeper
//        Zookeeper z = new Zookeeper("Caral", 20,za);
//
//        //Arraylist that will hold all of the actions performed
//        ArrayList<String> allActions = new ArrayList<>();
//
//        //While with our current program, the animal list will never change. This line here is to reduce the number of calls to getAnimals.
//        //If you were to implement a method that causes the zoo to gain/lose animals, you'll want to move this line down to line 31 so that the methods below are correctly called on the right set of animals.
//        ArrayList<Animal> curAnimals = AnnasZoo.getAllAnimals();
//
//        //for a period of 7 days
//        while(AnnasZoo.getDay() < 8){
//            int curDay = AnnasZoo.getDay();
//            //zookeeper must clock in,exercise, tell animals to sleep, clock out
//            allActions.add(z.clockIn() + "on Day " + curDay + ".");
//
//
//            //zookeeper wakes the animals & Animals Wake up
//            allActions.addAll(z.wakeAnimals(curAnimals));
//
//            //zookeeper roll calls the animals
//            allActions.addAll(z.rollCall(curAnimals));
//
//            //zookeeper feeds animals
//            allActions.addAll(z.feed(curAnimals));
//
//            //exercise animals
//            allActions.addAll(z.exercise(curAnimals));
//
//            //tell animals to sleep
//            allActions.addAll(z.putToSleep(curAnimals));
//
//            //zookeeper clocks out
//            allActions.add(z.clockOut() + "at the end of Day " + curDay + ".");
//
//            AnnasZoo.newDay();
//        }

        //Attempt to create output file for all actions performed in the program
//        try{
//            //Create the output file. If it already exists, it will overwrite the existing file.
//            FileWriter zooRecord = new FileWriter("dayatthezoo.out", false);
//            //If file creation is successful, write every action to the output file
//            for(String act: allActions) {
//                try{
//                    zooRecord.write(act + System.lineSeparator());
//                    if (act.contains("goes home")){
//                        //Separate each day with an empty line.
//                        zooRecord.write(System.lineSeparator());
//                    }
//                } catch (IOException errW) {
//                    //Error occurred when attempting to write to file.
//                    System.out.println("An error occurred.");
//                    errW.printStackTrace();
//                }
//            }
//            zooRecord.close();
//        } catch (IOException err) {
//            //An error occurred when attempting to create file.
//            System.out.println("An error occurred.");
//            err.printStackTrace();
//        }
    }

}
