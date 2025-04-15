package org.lesson2.answer2;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Answer2 {

    public static int searchUniqueMaxThirdNumber(List<Integer> numbers) {
        return Optional.ofNullable(numbers)
                .orElseThrow(() -> new IllegalArgumentException("Пустой список целых чисел. Невозможно найти максимальное число."))
                .stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("3-е наибольшее число отсутствует."));
    }
}