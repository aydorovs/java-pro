package org.lessons.lesson3;


public class Main {


    /**
     * Попробуйте реализовать собственный пул потоков.
     * <p>
     * В качестве аргументов конструктора пулу передается его емкость (количество рабочих потоков).
     * Как только пул создан, он сразу инициализирует и запускает потоки.
     * Внутри пула очередь задач на исполнение организуется через LinkedList<Runnable>.
     * При выполнении у пула потоков метода execute(Runnable), указанная задача должна попасть в очередь исполнения,
     * и как только появится свободный поток – должна быть выполнена. Также необходимо реализовать метод shutdown(),
     * после выполнения которого новые задачи больше не принимаются пулом (при попытке добавить задачу можно бросать IllegalStateException),
     * и все потоки для которых больше нет задач завершают свою работу.
     * <p>
     * Дополнительно можно добавить метод awaitTermination() без таймаута, работающий аналогично стандартным пулам потоков
     */
    public static void main(String[] args) {
        MyThreadPool pool = new MyThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int id = i;
            pool.execute(() -> {
                System.out.println("Task " + id + " выполнена потоком " + Thread.currentThread().getName());
                try {
                    Thread.sleep(300); // имитация работы
                } catch (InterruptedException ignored) {
                }
            });
        }

        pool.shutdown();
        pool.awaitTermination();

        System.out.println("Все задачи завершены");
    }
}
