package mvc;// PEK-MVCList/Person.java
 
public class Person {

    private String name;
    private int    age;
    private String personalData;

    Person(String n, int a) {
        name = n;
        age  = a;
        personalData = name + "(" + age + "yo)\nThis is " +
            "personal data on the aforementioned person...";
    }

    public String toString() {
        return name + "(" + age + "yo)";
    }

    String getData() {
        return personalData;
    }
}
