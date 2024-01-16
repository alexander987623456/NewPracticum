//TIP To <>Run</b> code, press <shortcut actionId="Run"/> or

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class Main {
    public static final String DATA_FILE_PATH = "PersonTestData.txt";

    public static void main(String[] args) {
        ArrayList<String[]> dataList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        do {

            String ID = getNonZeroLenString(scanner, "Enter ID; ");
            String firstName = getNonZeroLenString(scanner, "Enter firstName");
            String lastName = getNonZeroLenString(scanner, "Enter last name: ");
            String title = getNonZeroLenString(scanner, "Enter title: ");

            int YOB = getInt(scanner, "Enter year of birth; ");
            //ArrayList<String[]> dataList = new ArrayList<>();
            ArrayList<Object> list = new ArrayList<>();
            String[] newData = {ID, firstName, lastName, title, String.valueOf(YOB)};

            dataList.add(newData);
            writeDataToFile(dataList);
            if (!getYNConfirm(scanner, "Do you want to add more data?")) {
                break;
            }
            //not needed below?
            //list.add(ID);
            //list.add(firstName);
            // list.add(lastName);
            //list.add(title);
            //list.add(YOB);
            //System.out.println("ArrayList Contents: " + list);
            //String[] dataElements = list.toArray(new String[0]);
            //System.out.println("Data Elements: " + String.join(",", dataElements));


        } while (true);


        System.out.println("ArrayList Contents:");
        for (String[] data : dataList) {
            System.out.println(String.join(",", data));
        }
        scanner.close();


    }

    public static String getNonZeroLenString(Scanner pipe, String prompt) {

        String retString;
        do {
            System.out.print("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);

        return retString;

    }

    public static int getInt(Scanner pipe, String prompt) {
        int retVal = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + ": ");
            if (pipe.hasNextInt()) {
                retVal = pipe.nextInt();
                pipe.nextLine();
                done = true;
            } else {
                trash = pipe.nextLine();
                System.out.println("You must enter an int: " + trash);
            }
        } while (!done);

        return retVal;
    }
    public static boolean getYNConfirm(Scanner pipe, String prompt) {
        boolean retVal;
        String response;
        boolean gotAVal;

        do {
            System.out.print("\n" + prompt + " [Y/N] ");
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                gotAVal = true;
                retVal = true;
            } else if (response.equalsIgnoreCase("N")) {
                gotAVal = true;
                retVal = false;
            } else {
                System.out.println("You must answer [Y/N]! " + response);
                gotAVal = false;
                retVal = false;
            }

        } while (!gotAVal);

        return retVal;
    }
    public static void writeDataToFile(ArrayList<String[]> dataList) {
        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(Path.of(DATA_FILE_PATH), CREATE));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out))) {


            for (String[] data : dataList) {
                writer.write(String.join(",", data));
                writer.newLine();
            }

            System.out.println("Data written to " + DATA_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

        







