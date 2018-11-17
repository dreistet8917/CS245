package BST;
//package lab04;

import java.util.NoSuchElementException;

public class QueueList<E> implements Queue<E> {
	private Node<E> front;
	private Node<E> rear;
	private int size = 0;
	
	public QueueList(E it) {
		front = rear =new Node<E>(it);
		size++;
	}
	
	public QueueList() {
		front = rear = new Node<E>(null);
		size = 0;
	}
	

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	@Override
	public E front() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return front.getElement();
	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()) {
			throw new NoSuchElementException();
		} else {
			E frontE = front.getElement();
			front = front.getNext();
			size --;
			return frontE;
		}
	}

	@Override
	public void enqueue(E it) {
		// TODO Auto-generated method stub
		Node<E> newRear = new Node<E>(it);
		if (size == 0) {
			front = newRear;
			rear = newRear;
			size++;
		} else {
		rear.setNext(newRear);
		rear = newRear;
		size++;
		}
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		front = null;
		rear = null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}
	
}
