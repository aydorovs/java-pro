package org.lessons.lesson1.test;

import org.lessons.lesson1.annotation.AfterSuite;
import org.lessons.lesson1.annotation.AfterTest;
import org.lessons.lesson1.annotation.BeforeSuite;
import org.lessons.lesson1.annotation.BeforeTest;
import org.lessons.lesson1.annotation.CsvSource;
import org.lessons.lesson1.annotation.Test;

public class MyTests {

    @BeforeSuite
    public static void beforeAll() {
        System.out.println("\nBeforeSuite: Инициализация\n");
    }

    @AfterSuite
    public static void afterAll() {
        System.out.println("\nAfterSuite: Очистка\n");
    }

    @BeforeTest
    public void firstBeforeEach() {
        System.out.println("\nBeforeTest 1: перед каждым тестом");
    }

    @BeforeTest
    public void secondBeforeEach() {
        System.out.println("BeforeTest 2: перед каждым тестом");
    }

    @AfterTest
    public void afterEach() {
        System.out.println("AfterTest: после каждого теста\n");
    }

    @Test(priority = 7)
    public void firstHighPriorityTest() {
        System.out.println("Выполняется тест с высоким приоритетом 7");
    }

    @Test(priority = 10)
    public void secondHighPriorityTest() {
        System.out.println("Выполняется тест с высоким приоритетом 11");
    }

    @Test
    public void mediumPriorityTest() {
        System.out.println("Выполняется тест со средним приоритетом 5");
    }

    @Test(priority = 2)
    @CsvSource("10, Java, 20, true")
    public void parameterizedTest(int a, String b, int c, boolean d) {
        System.out.println("Приоритет 2. Параметризованный тест: " + a + ", " + b + ", " + c + ", " + d);
    }
}
