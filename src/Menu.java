import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
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
                    addPerson();
                    break;
                case 2:
                    listPeople();
                    break;
                case 3:
                    createQuestion();
                    break;
                case 4:
                    removeQuestion();
                    break;
//                case 5:
//                searchByUser();
//                    break;
                case 6:
                    System.exit(0);

            }
        }
        while (option > 5 || option <= 0);
    }

    public void removeQuestion() {
        showForm();
        Scanner myObj = new Scanner(System.in).useLocale(Locale.US);
        System.out.print("Question for deletion: ");
        int numberQ = myObj.nextInt();
        while (numberQ <= 4) {
            numberQ = myObj.nextInt();
        }
        try {
            ArrayList<String> content = (ArrayList<String>) Files.readAllLines(Paths.get("utils\\form.txt"));
            System.out.println(content.remove(numberQ - 1));
            System.out.println(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        app();
    }

        public void showForm() {
        int i = 0;

        try {
            List<String> content = Files.readAllLines(Paths.get("utils\\form.txt"));
            System.out.println("==================================================\n" +
                    "                 FORMULARY\n" +
                    "        Please answer the questions\n" +
                    "==================================================");
            for(String contents : content) {
                i++;
                System.out.println("[" + i + "] ➤ " + contents);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

        String question = myObj.nextLine();
        while (question.length() < 10 || question.length() > 100) {
            question = myObj.nextLine();
        }


        numberQuestion += 1;


        try {
            FileWriter myFile = new FileWriter("utils\\form.txt");
            myFile.write(content);
            myFile.write(question);
            myFile.write("\n");
            myFile.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getCause());
        }

        app();

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
