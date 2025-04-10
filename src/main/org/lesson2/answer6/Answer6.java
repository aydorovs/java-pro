package org.lesson2.answer6;


import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Answer6 {

    private Answer6() {
    }

    public static Map<String, Integer> searchWordCount(String input) {
        return Arrays.stream(input.split("\\s+"))
                .collect(Collectors.toMap(
                        word -> word,
                        word -> 1,
                        Integer::sum
                ));
    }
}