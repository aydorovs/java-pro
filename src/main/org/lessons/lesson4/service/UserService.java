package org.lessons.lesson4.service;

import org.lessons.lesson4.entity.User;
import org.lessons.lesson4.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements CommandLineRunner {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("\n----  Старт работы с пользователями ----");

        User user1 = new User("Тест1");
        userRepository.save(user1);
        User user2 = new User("Тест2");
        userRepository.save(user2);
        User user3 = new User("Тест3");
        userRepository.save(user3);
        User user4 = new User("Тест4");
        userRepository.save(user4);

        User userExist = userRepository.findById(3L).orElseThrow();
        System.out.println("---- Пользователь c id = 3 найден: " + userExist.getUsername());

        userRepository.deleteById(4L);
        try {
            userRepository.findById(4L).orElseThrow();
        } catch (Exception e) {
            System.out.println("---- Пользователь c id = 4 не найден");
        }


        List<User> allUsers = userRepository.findAll();
        System.out.println("---- Найдено пользователей: \n" + allUsers.size());
    }
}