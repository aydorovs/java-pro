package org.lesson2.answer4;


import org.lesson2.answer3.dto.Employee;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.lesson2.answer3.Answer3.POSITION_ENGINEER;

public class Answer4 {

    private Answer4() {
    }

    public static int searchAverageAgeEngineerEmployees(List<Employee> employees) {
        return Optional.ofNullable(employees)
                .orElse(Collections.emptyList())
                .stream()
                .filter(employee -> employee.getPosition().equals(POSITION_ENGINEER))
                .collect(Collectors.collectingAndThen(Collectors.averagingInt(Employee::getAge), Double::intValue));
    }
}