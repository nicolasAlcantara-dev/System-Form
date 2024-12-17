import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    public void app() {
        showForm();
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


        Person person1 = new Person(name, age, email, height);
        people.add(person1);
        System.out.println(person1);

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
}
