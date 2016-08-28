package ru.devjava.averchenkov.threads.buffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Реальная очередь.
 *
 * @author Averchenkov R.A.
 */
public class RealBuffer extends Buffer{
    private Queue<Integer> elems = new LinkedList<Integer>();

    private final Integer MAX_COUNT_ITEMS_IN_QUEUE = 100;

    private Object lockPut = new Object();
    private Object lockGet = new Object();

    public void put(Integer elem) throws InterruptedException {
        synchronized (lockPut) {
            while (this.elems.size() >= MAX_COUNT_ITEMS_IN_QUEUE) {
                lockPut.wait();
            }
        }
        synchronized (lockGet) {
            this.elems.add(elem);
            System.out.println(this.elems.size());
            lockGet.notifyAll();
        }
    }

    public Integer get() throws InterruptedException {
        synchronized (lockGet) {
            while (this.elems.size() == 0) {
                lockGet.wait();
            }
        }
        Integer result = null;
        synchronized (lockPut) {
            result = this.elems.poll();
            lockPut.notifyAll();
        }
        return result;
    }
}
