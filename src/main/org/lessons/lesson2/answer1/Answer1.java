package org.lessons.lesson2.answer1;


import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Answer1 {

    public static Integer searchMaxThirdNumber(List<Integer> numbers) {
        return Optional.ofNullable(numbers)
                .orElseThrow(() -> new IllegalArgumentException("Пустой список целых чисел. Невозможно найти максимальное число."))
                .stream()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("3-е наибольшее число отсутствует."));
    }
}