package org.lessons.lesson2.answer6;


import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Answer6 {

    public static Map<String, Long> searchWordCount(String input) {
        return Arrays.stream(input.split("\\s+"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}