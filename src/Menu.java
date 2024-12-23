import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                case 3:
                    createQuestion();
                    break;
//                case 4:
//                    removeQuestion();
//                    break;
//                case 5:
//                searchByUser();
//                    break;
                case 6:
                    System.exit(0);

            }
        }
        while (option > 5 || option <= 0);
    }

    public void showMenu() {
        Path fileMenu = Path.of("utils\\menu.txt");
        try {
            String content = Files.readString(fileMenu);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

        public void showForm() {
            Path fileForm = Path.of("utils\\form.txt");
        try {
            String content = Files.readString(fileForm);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addPerson();
    }

    public void createQuestion() {
        Scanner myObj = new Scanner(System.in).useLocale(Locale.US);
        int numberQuestion = 5;
        Path fileForm = Path.of("utils\\form.txt");
        String content;

        try {
            content = Files.readString(fileForm);
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        System.out.print("[" + numberQuestion + "]" + " ➤ ");
        String question = myObj.nextLine();
        while (question.length() < 10 || question.length() > 100) {
            System.out.print("[" + numberQuestion + "]" + " ➤ ");
            question = myObj.nextLine();
        }


        numberQuestion += 1;


        try {
            FileWriter myFile = new FileWriter("utils\\form.txt");
            myFile.write(content);
            myFile.write("[" + numberQuestion + "]" + " ➤ " + question);
            myFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getCause());
        }

        app();

    }

    public void addPerson() {
        Scanner myObj = new Scanner(System.in).useLocale(Locale.US);
        ArrayList<Person> people = new ArrayList<Person>();

        String name = myObj.nextLine();
        while (name.length() < 4 || name.length() > 15) {
            System.out.print("Enter with a correct name: ");
            name = myObj.nextLine();
        }

        int age = Integer.parseInt(myObj.nextLine());
        while (age < 0 || age > 100) {
            System.out.println("A true age");
            age = Integer.parseInt(myObj.nextLine());
        }

        String email = myObj.nextLine();
        while (email.length() <= 10) {
            System.out.print("Enter with a correct email: ");
            email = myObj.nextLine();
        }

        float height = Float.parseFloat(myObj.nextLine());
        while (height < 140 || height > 300) {
            System.out.print("Enter with a correct height: ");
            height = Float.parseFloat(myObj.nextLine());
        }

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
        for (File fileEntry : folder.listFiles()) {
            System.out.println(fileEntry.getName());
        }
        option = 0;
    }



}
