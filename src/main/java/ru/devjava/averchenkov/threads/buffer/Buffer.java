package ru.devjava.averchenkov.threads.buffer;

/**
 * Неэффективная очередь.
 *
 * @author Averchenkov R.A.
 */
public class Buffer {
    private Integer elem = null;

    public synchronized void put(Integer elem) throws InterruptedException {
        while (this.elem != null){
            this.wait();
        }
        this.elem = elem;
        this.notifyAll();
    }

    public synchronized Integer get() throws InterruptedException {
        while (elem == null){
            this.wait();
        }
        Integer result = this.elem;
        this.elem = null;
        this.notifyAll();
        return result;
    }
}
