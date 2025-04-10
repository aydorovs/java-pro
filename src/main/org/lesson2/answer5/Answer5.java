package org.lesson2.answer5;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class Answer5 {

    private Answer5() {
    }

    public static String searchMaxLengthWord(String[] words) {
        return Arrays.stream(
                        Optional.ofNullable(words)
                                .orElseThrow(() -> new IllegalArgumentException("Некорректное количество элементов в массиве"))
                ).max(Comparator.comparingInt(String::length))
                .orElseThrow(() -> new IllegalArgumentException("Некорректное количество элементов в массиве"));
    }
}