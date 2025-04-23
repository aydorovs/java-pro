package org.lesson3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class MyThreadPool {

    private final LinkedList<Runnable> taskQueue = new LinkedList<>();
    private final List<Worker> workers = new ArrayList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition taskAvailable = lock.newCondition();
    private final AtomicBoolean isShutdown = new AtomicBoolean(false);

    public MyThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    public void execute(Runnable task) {
        lock.lock();
        try {
            if (isShutdown.get()) {
                throw new IllegalStateException("ThreadPool остановлен. Нет новых задач.");
            }
            taskQueue.add(task);
            taskAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    public void shutdown() {
        lock.lock();
        try {
            isShutdown.set(true);
            taskAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void awaitTermination() {
        for (Worker worker : workers) {
            try {
                worker.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private class Worker extends Thread {
        public void run() {
            while (true) {
                Runnable task;

                lock.lock();
                try {
                    while (taskQueue.isEmpty() && !isShutdown.get()) {
                        taskAvailable.await();
                    }

                    if (taskQueue.isEmpty() && isShutdown.get()) {
                        break;
                    }

                    task = taskQueue.poll();
                } catch (InterruptedException e) {
                    break;
                } finally {
                    lock.unlock();
                }

                if (task != null) {
                    try {
                        task.run();
                    } catch (RuntimeException e) {
                        System.err.println("Ошибка при выполнении задачи: " + e.getMessage());
                    }
                }
            }
        }
    }
}