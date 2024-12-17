public class Person {
    private String name;
    private int age;
    private String email;
    private float height;

    public Person(String name, int age, String email, float height) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public float getHeight() {
        return height;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "People{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", height=" + height +
                '}';
    }
}
