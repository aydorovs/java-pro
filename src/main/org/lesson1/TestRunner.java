package org.lesson1;

import org.lesson1.annotation.AfterSuite;
import org.lesson1.annotation.AfterTest;
import org.lesson1.annotation.BeforeSuite;
import org.lesson1.annotation.BeforeTest;
import org.lesson1.annotation.CsvSource;
import org.lesson1.annotation.Test;
import org.lesson1.test.MyTests;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void runTests(Class<?> currentClass) throws Exception {
        Method beforeSuite = null;
        Method afterSuite = null;
        List<Method> beforeTestMethods = new ArrayList<>();
        List<Method> afterTestMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();

        for (Method method : currentClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(BeforeSuite.class) && Modifier.isStatic(method.getModifiers())) {
                // Проверяем что только один раз инициализируем beforeSuite
                if (beforeSuite != null) {
                    throw new RuntimeException("Должен быть только один метод с @BeforeSuite");
                }
                beforeSuite = method;
            }
            if (method.isAnnotationPresent(AfterSuite.class) && Modifier.isStatic(method.getModifiers())) {
                // Проверяем что только один раз инициализируем afterSuite
                if (afterSuite != null) {
                    throw new RuntimeException("Должен быть только один метод с @AfterSuite");
                }
                afterSuite = method;
            }
            if (method.isAnnotationPresent(BeforeTest.class)) {
                beforeTestMethods.add(method);
            }
            if (method.isAnnotationPresent(AfterTest.class)) {
                afterTestMethods.add(method);
            }
            if (method.isAnnotationPresent(Test.class)) {
                Test annotation = method.getAnnotation(Test.class);
                // Проверяем есть ли методы с некорректным значением priority в аннотации @Test
                int priority = annotation.priority();
                if (priority < 1 || priority > 10) {
                    throw new IllegalArgumentException("Значение priority в аннотации @Test для метода "
                            + method.getName() +
                            " должно быть в пределах от 1 до 10 включительно. " +
                            "Текущее значение priority = " + priority);
                }
                testMethods.add(method);
            }
        }

        // Сортировка по приоритету
        testMethods.sort((m1, m2) ->
                Integer.compare(m2.getAnnotation(Test.class).priority(), m1.getAnnotation(Test.class).priority()));

        if (beforeSuite != null) beforeSuite.invoke(null);

        Object instance = currentClass.getDeclaredConstructor().newInstance();

        for (Method testMethod : testMethods) {
            if (!beforeTestMethods.isEmpty()) {
                for (Method beforeTestMethod : beforeTestMethods) {
                    beforeTestMethod.invoke(instance);
                }
            }

            if (testMethod.isAnnotationPresent(CsvSource.class)) {
                String[] params = testMethod.getAnnotation(CsvSource.class).value().split(",\\s*");
                Class<?>[] paramTypes = testMethod.getParameterTypes();
                Object[] parsedParams = new Object[params.length];
                if (params.length != paramTypes.length) {
                    throw new IllegalArgumentException("Некорректное количество параметров для метода " + testMethod.getName());
                }
                for (int i = 0; i < params.length; i++) {
                    parsedParams[i] = parseParam(params[i], paramTypes[i]);
                }
                testMethod.invoke(instance, parsedParams);
            } else {
                testMethod.invoke(instance);
            }

            if (!afterTestMethods.isEmpty()) {
                for (Method afterTestMethod : afterTestMethods) {
                    afterTestMethod.invoke(instance);
                }
            }
        }

        if (afterSuite != null) afterSuite.invoke(null);
    }

    private static Object parseParam(String param, Class<?> type) {
        if (type == int.class) return Integer.parseInt(param);
        if (type == boolean.class) return Boolean.parseBoolean(param);
        if (type == String.class) return param;
        throw new IllegalArgumentException("Unsupported parameter type: " + type);
    }

    public static void main(String[] args) throws Exception {
        runTests(MyTests.class);
    }
}
