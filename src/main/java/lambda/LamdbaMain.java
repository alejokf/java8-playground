package lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LamdbaMain {

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alejandro", 35));
        people.add(new Person("John", 25));

        // Lambda
        List<String> names = people.stream()
                .map(person -> person.getName())
                .collect(Collectors.toList());
        for (String name : names) {
            System.out.println(name);
        }

        //Method reference
        people.stream()
                .map(Person::getName) // Method from current object
                .map(StringUtil::toUpperCase) // Static method
                .forEach(System.out::println); // Method from another object
    }
}
