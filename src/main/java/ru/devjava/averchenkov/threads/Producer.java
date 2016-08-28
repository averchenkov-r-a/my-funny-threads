package ru.devjava.averchenkov.threads;

import ru.devjava.averchenkov.threads.buffer.Buffer;

/**
 * Проиводитель.
 *
 * @author Averchenkov R.A.
 */
public class Producer implements Runnable {
    private Buffer buffer;
    private Integer startNumer;

    public Producer(Buffer buffer, Integer startNumer) {
        this.buffer = buffer;
        this.startNumer = startNumer;
    }

    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("producer #" + startNumer);
                buffer.put(startNumer);
                startNumer++;
                Thread.sleep((int)(1000 * Math.random()));
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }
        }
    }
}
