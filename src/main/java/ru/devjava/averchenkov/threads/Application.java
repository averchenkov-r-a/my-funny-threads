package ru.devjava.averchenkov.threads;

import ru.devjava.averchenkov.threads.buffer.Buffer;
import ru.devjava.averchenkov.threads.buffer.RealBuffer;
import ru.devjava.averchenkov.threads.buffer.TrueBuffer;

/**
 * Класс запуска.
 *
 * @author Averchenkov R.A.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new RealBuffer();
        Thread threadConsumer1 = new Thread(new Consumer(buffer));
        Thread threadConsumer2 = new Thread(new Consumer(buffer));
        Thread threadProducer1 = new Thread(new Producer(buffer, 100));
        Thread threadProducer2 = new Thread(new Producer(buffer, 1000));
        Thread threadProducer3 = new Thread(new Producer(buffer, 10000));

        // Стартуем
        threadConsumer1.start();
        threadConsumer2.start();

        threadProducer1.start();
        threadProducer2.start();
        threadProducer3.start();

        Thread.sleep(10000);

        // Убиваем
        threadProducer1.interrupt();
        threadProducer2.interrupt();
        threadProducer3.interrupt();

        threadConsumer1.interrupt();
        threadConsumer2.interrupt();
    }
}
