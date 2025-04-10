package org.lesson2.answer1;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class Answer1 {

    private Answer1() {
    }

    public static int searchMaxThirdNumber(int[] numbers) {
        return Arrays.stream(
                        Optional.ofNullable(numbers)
                                .orElseThrow(() -> new IllegalArgumentException("Пустой список целых чисел. Невозможно найти максимальное число."))
                ).boxed()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("3-е наибольшее число отсутствует."));
    }
}