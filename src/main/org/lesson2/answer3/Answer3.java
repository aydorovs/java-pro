package org.lesson2.answer3;


import org.lesson2.answer3.dto.Employee;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Answer3 {

    public static final String POSITION_ENGINEER = "Engineer";
    public static final String POSITION_NOT_ENGINEER = "NotEngineer";

    private Answer3() {
    }

    public static List<String> searchOldestEmployees(List<Employee> employees) {
        return Optional.ofNullable(employees)
                .orElse(Collections.emptyList())
                .stream()
                .filter(employee -> employee.getPosition().equals(POSITION_ENGINEER))
                .sorted(Comparator.comparing(Employee::getAge).reversed())
                .limit(3)
                .map(Employee::getName)
                .toList();
    }
}
