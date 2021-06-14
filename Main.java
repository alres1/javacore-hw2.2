package JavaCore.hw2_2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        //persons.forEach(System.out::println);
        System.out.println("===============================================");

        //количество несовершеннолетних
        Stream<Person> minor = persons.stream().filter(x -> x.getAge() < 18);
        System.out.println(minor.count());
        System.out.println("===============================================");

        //список фамилий призывников
        List<String> recruit = persons.stream()
                .filter(x -> x.getAge() >= 18 && x.getAge() <= 27)
                .filter(x -> x.getSex() == Sex.MAN )
                .map(x-> x.getFamily())
                .collect(Collectors.toList());
        recruit.forEach(System.out::println);
        System.out.println("===============================================");

        //список потенциально работоспособных людей с высшим образованием
        List<String> worker = persons.stream()
                .filter(x -> x.getEducation() == Education.HIGHER)
                .filter(x-> x.getSex() == Sex.WOMAN ? x.getAge() >= 18 && x.getAge() <= 60 : x.getAge() >= 18 && x.getAge() <= 65)
                .sorted(Comparator.comparing(x-> x.getFamily()))
                .map(x-> x.getFamily())
                .collect(Collectors.toList());
        worker.forEach(System.out::println);
    }
}
