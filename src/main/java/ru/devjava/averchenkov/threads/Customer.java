package ru.devjava.averchenkov.threads;

import ru.devjava.averchenkov.threads.buffer.Buffer;

/**
 * Потребитель.
 *
 * @author Averchenkov R.A.
 */
public class Customer implements Runnable {
    private Buffer buffer;

    public Customer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Integer elem = this.buffer.get();
                System.out.println("customer #" + elem);
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
