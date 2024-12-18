import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    int option = 0;
    int index = 1;
    public void app() {

        do {
            showMenu();

            Scanner myOption = new Scanner((System.in));
            option = myOption.nextInt();

            switch (option) {
                case 1:
                    showForm();
                    break;
                case 2:
                    listPeople();
                    break;
                case 6:
                    System.exit(0);

            }
        }
        while (option > 5 || option <= 0);
    }

    public void showMenu() {
        try {
            File form = new File("utils\\menu.txt");
            Scanner myReader = new Scanner(form);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:" + e);
            e.printStackTrace();
        }
    }

    public void showForm() {
        try {
            File form = new File("utils\\form.txt");
            Scanner myReader = new Scanner(form);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred:" + e);
            e.printStackTrace();
        }
        addPerson();
    }

    public void addPerson() {
        Scanner myObj = new Scanner(System.in).useLocale(Locale.US);
        ArrayList<Person> people = new ArrayList<Person>();

        //        System.out.println(userName);
        String name = myObj.nextLine();
        int age = Integer.parseInt(myObj.nextLine());
        String email = myObj.nextLine();
        float height = Float.parseFloat(myObj.nextLine());


        Person personObj = new Person(name, age, email, height);
        people.add(personObj);
        createFile(personObj);
        option = 0;
    }

    public void createFile(Person person) {

        try {
            FileWriter myFile = new FileWriter("forms\\"+ index + "-" + person.getName().toUpperCase());
            myFile.write(person.toString());
            myFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        index++;
    }

    public void listPeople() {
        File folder = new File("forms/");

        System.out.println("People found: ");
        for (final File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
        }
        option = 0;
    }

}
