package imperative;

import java.util.List;

public class Approaches {

    public static void main(String[] args) {
        List<Person> listPerson = List.of(
            new Person("John", Gender.MALE),
            new Person("Maria", Gender.FEMALE),
            new Person("Aisha", Gender.FEMALE),
            new Person("Tania", Gender.FEMALE),
            new Person("Miguel", Gender.MALE)
        );

        listPerson.stream()
                .filter(person -> person.gender == Gender.FEMALE)
                .forEach(System.out::println);
    }

    static class Person{
        private final String name;
        private final Gender gender;

        Person(String name, Gender gender) {
            this.name = name;
            this.gender = gender;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", gender=" + gender +
                    '}';
        }
    }

    enum Gender{
        MALE, FEMALE
    }
}
