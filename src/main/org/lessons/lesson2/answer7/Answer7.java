package org.lessons.lesson2.answer7;


import java.util.Comparator;
import java.util.List;

public class Answer7 {

    public static void sort(List<String> strings) {
        strings.stream()
                .sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder()))
                .forEach(System.out::println);
    }
}