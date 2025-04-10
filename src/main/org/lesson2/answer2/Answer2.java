package org.lesson2.answer2;


import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public class Answer2 {

    private Answer2() {
    }

    public static int searchUniqueMaxThirdNumber(int[] numbers) {
        return Arrays.stream(
                        Optional.ofNullable(numbers)
                                .orElseThrow(() -> new IllegalArgumentException("Пустой список целых чисел. Невозможно найти максимальное число."))
                ).distinct()
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(2)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("3-е наибольшее число отсутствует."));
    }
}