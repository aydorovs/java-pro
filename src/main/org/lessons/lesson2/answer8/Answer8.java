package org.lessons.lesson2.answer8;


import java.util.Arrays;
import java.util.Comparator;

public class Answer8 {

    public static String searchLargestWord(String[] words) {
        return Arrays.stream(words)
                .flatMap(s -> Arrays.stream(s.split("\\s+")))
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}