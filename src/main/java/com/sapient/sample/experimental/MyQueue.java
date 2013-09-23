package com.sapient.sample.experimental;

import java.util.concurrent.Semaphore;

/**
 * Blocking Queue implementation as an array.
 * I have not implemented any interface. Idea was to implement
 * the blocking method and put up Unit Testing scenarios. 
 * 
 * @author gsing5 
 * 
 */
public class MyQueue<E> {

	private int putPosition, takePosition = 0;
	private final Semaphore putSemaphore;
	private final Semaphore getSemaphore;
	private int size = 0;
	private final E[] items;

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return getSemaphore.availablePermits() == 0;
	}
	
	public boolean isFull() {
		return putSemaphore.availablePermits() == 0;
	}

	public MyQueue(int capacity) {
		this.items = (E[]) new Object[capacity];
		putSemaphore = new Semaphore(capacity);
		getSemaphore = new Semaphore(0);
	}

	public void put(E e) throws InterruptedException {
		putSemaphore.acquire();
		putElement(e);
		getSemaphore.release();

	}

	private synchronized void putElement(E e) {
		int i = putPosition;
		items[i] = e;
		size++;
		putPosition = (++i == items.length) ? 0 : i;
	}

	public E get() throws InterruptedException {
		getSemaphore.acquire();
		E x = getElement();
		putSemaphore.release();
		return x;
	}

	private synchronized E getElement() {
		int i = takePosition;
		E x = items[i];
		size--;
		takePosition = (++i == items.length) ? 0 : i;
		return x;
	}

}
