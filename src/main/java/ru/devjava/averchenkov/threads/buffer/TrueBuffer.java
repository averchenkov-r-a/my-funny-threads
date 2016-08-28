package ru.devjava.averchenkov.threads.buffer;

/**
 * Правильная очередь.
 *
 * @author Averchenkov R.A.
 */
public class TrueBuffer extends Buffer {
    private Integer elem = null;

    private Object lockPut = new Object();
    private Object lockGet = new Object();

    public void put(Integer elem) throws InterruptedException {
        synchronized (lockPut) {
            while (this.elem != null) {
                lockPut.wait();
            }
            this.elem = elem;

            synchronized (lockGet) {
                lockGet.notifyAll();
            }
        }
    }

    public Integer get() throws InterruptedException {
        synchronized (lockGet) {
            while (this.elem == null) {
                lockGet.wait();
            }
            Integer result = null;
            synchronized (lockPut) {
                result = this.elem;
                this.elem = null;
                lockPut.notifyAll();
            }
            return result;
        }
    }
}
