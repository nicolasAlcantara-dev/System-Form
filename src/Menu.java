import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public void app() {
        showForm();
        addPerson();
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
            System.out.println("An error ocurred:" + e);
            e.printStackTrace();
        }
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

    }

    public void createFile(Person person) {
        int index = 1;
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

}
