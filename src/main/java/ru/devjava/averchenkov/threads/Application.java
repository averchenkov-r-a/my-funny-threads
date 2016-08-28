package ru.devjava.averchenkov.threads;

import ru.devjava.averchenkov.threads.buffer.Buffer;
import ru.devjava.averchenkov.threads.buffer.RealBuffer;

/**
 * Класс запуска.
 *
 * @author Averchenkov R.A.
 */
public class Application {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new RealBuffer();
        Thread threadCustomer = new Thread(new Customer(buffer));
        Thread threadProducer1 = new Thread(new Producer(buffer, 1));
        Thread threadProducer2 = new Thread(new Producer(buffer, 1000));

        threadCustomer.start();
        threadProducer1.start();
        threadProducer2.start();

        Thread.sleep(10000);
        threadProducer1.interrupt();
        threadProducer2.interrupt();
        threadCustomer.interrupt();
    }
}
