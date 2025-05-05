package org.lessons;

import org.lessons.lesson4.model.User;
import org.lessons.lesson4.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

@ComponentScan
public class SpringCrudApplication {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringCrudApplication.class);

        UserService userService = context.getBean(UserService.class);

        // Создаем пользователей
        User user1 = userService.createUser("Тест1");
        User user2 = userService.createUser("Тест2");
        System.out.println("Создаем пользователей: " + user1 + ", " + user2);

        // Получаем всех пользователей
        List<User> allUsers = userService.getAllUsers();
        System.out.println("Все пользователи: " + allUsers);

        // Получение пользователя по ID
        User foundUser = userService.getUserById(user1.getId());
        System.out.println("Нашли пользователя по ID: " + foundUser);

        // Обновляем пользователя
        foundUser.setUsername("Тест1_обновленный");
        userService.updateUser(foundUser);
        System.out.println("Обновленный пользователь: " + userService.getUserById(foundUser.getId()));

        // Удаляем пользователя
        userService.deleteUser(user2.getId());
        System.out.println("Все пользователи после удаления: " + userService.getAllUsers());

        context.close();
    }
}