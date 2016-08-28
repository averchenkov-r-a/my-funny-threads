package ru.devjava.averchenkov.threads;

import ru.devjava.averchenkov.threads.buffer.Buffer;

/**
 * Потребитель.
 *
 * @author Averchenkov R.A.
 */
public class Consumer implements Runnable {
    private Buffer buffer;

    public Consumer(Buffer buffer) {
        this.buffer = buffer;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Integer elem = this.buffer.get();
                System.out.println("consumer #" + elem);
                Thread.sleep((int)(500 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
