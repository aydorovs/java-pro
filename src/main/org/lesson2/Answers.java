package org.lesson2;


import org.lesson2.answer1.Answer1;
import org.lesson2.answer2.Answer2;
import org.lesson2.answer3.Answer3;
import org.lesson2.answer3.dto.Employee;
import org.lesson2.answer4.Answer4;
import org.lesson2.answer5.Answer5;
import org.lesson2.answer6.Answer6;
import org.lesson2.answer7.Answer7;
import org.lesson2.answer8.Answer8;

import java.util.ArrayList;
import java.util.List;

import static org.lesson2.answer3.Answer3.POSITION_ENGINEER;
import static org.lesson2.answer3.Answer3.POSITION_NOT_ENGINEER;

public class Answers {

    public static void main(String[] args) throws Exception {
        /*
         * 1) Найдите в списке целых чисел 3-е наибольшее число (пример: 5 2 10 9 4 3 10 1 13 => 10)
         *
         * **/
        int[] numbers1 = {5, 2, 10, 9, 4, 3, 10, 1, 13};
        System.out.println("Задача 1. Ответ: " + Answer1.searchMaxThirdNumber(numbers1));

        /*
         * 2) Найдите в списке целых чисел 3-е наибольшее «уникальное» число (пример: 5 2 10 9 4 3 10 1 13 => 9,
         * в отличие от прошлой задачи здесь разные 10 считает за одно число)
         *
         * **/
        int[] numbers2 = {5, 2, 10, 9, 4, 3, 10, 1, 13};
        System.out.println("Задача 2. Ответ: " + Answer2.searchUniqueMaxThirdNumber(numbers2));

        /*
         * 3) Имеется список объектов типа Сотрудник (имя, возраст, должность),
         * необходимо получить список имен 3 самых старших сотрудников с должностью «Инженер», в порядке убывания возраста
         *
         * **/
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Name1", 40, POSITION_ENGINEER));
        employees.add(new Employee("Name2", 21, POSITION_NOT_ENGINEER));
        employees.add(new Employee("Name3", 32, POSITION_ENGINEER));
        employees.add(new Employee("Name4", 23, POSITION_NOT_ENGINEER));
        employees.add(new Employee("Name5", 24, POSITION_ENGINEER));
        employees.add(new Employee("Name6", 55, POSITION_NOT_ENGINEER));
        employees.add(new Employee("Name7", 26, POSITION_ENGINEER));
        employees.add(new Employee("Name8", 25, POSITION_NOT_ENGINEER));
        employees.add(new Employee("Name9", 66, POSITION_ENGINEER));
        employees.add(new Employee("Name10", 25, POSITION_NOT_ENGINEER));
        employees.add(new Employee("Name11", 26, POSITION_ENGINEER));
        employees.add(new Employee("Name12", 29, POSITION_NOT_ENGINEER));
        employees.add(new Employee("Name13", 26, POSITION_ENGINEER));
        System.out.println("Задача 3. Ответ: " + Answer3.searchOldestEmployees(employees));

        /*
         * 4) Имеется список объектов типа Сотрудник (имя, возраст, должность),
         * посчитайте средний возраст сотрудников с должностью «Инженер»
         *
         * **/
        System.out.println("Задача 4. Ответ: " + Answer4.searchAverageAgeEngineerEmployees(employees));

        /*
         * 5) Найдите в списке слов самое длинное
         *
         * **/
        System.out.println("Задача 5. Ответ: " + Answer5.searchMaxLengthWord(
                new String[]{"apple", "banana", "cherry", "grapefruit", "mango", "lemon"}
        ));

        /*
         * 6) Имеется строка с набором слов в нижнем регистре, разделенных пробелом.
         * Постройте хеш-мапы, в которой будут хранится пары: слово - сколько раз оно встречается во входной строке
         *
         * **/
        System.out.println("Задача 6. Ответ: " + Answer6.searchWordCount(
                "apple banana apple cherry grapefruit banana cherry cherry"
        ));

        /*
         * 7) Отпечатайте в консоль строки из списка в порядке увеличения длины слова,
         * если слова имеют одинаковую длины, то должен быть сохранен алфавитный порядок
         *
         * **/
        System.out.println("Задача 7. Ответ: ");
        Answer7.sort(
                List.of("apple", "banana", "grapefruit", "cherry", "date", "fig", "grape")
        );

        /*
         * 8) Имеется массив строк, в каждой из которых лежит набор из 5 слов,
         * разделенных пробелом, найдите среди всех слов самое длинное,
         * если таких слов несколько, получите любое из них
         *
         * **/
        String[] words = {
                "apple banana cherry date fig",
                "grape lemon mango orange plum",
                "apple banana cherry date fig grapefruit"
        };
        System.out.println("Задача 8. Ответ: " + Answer8.searchLargestWord(words));
    }
}
