package BST;
//package lab04;

import java.util.EmptyStackException;

public class StackList<E> implements Stack<E> {
	private Node<E> top;
	private int size = 0;

	public StackList(E it) {
		top = new Node<E>(it);
		size++;
	}

	public StackList() {
		top = new Node<E>(null);
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			return top.getElement();
		}
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			throw new EmptyStackException();
		} else {
			E topElement = top.getElement();
			top = top.getNext();
			size--;
			return topElement;
		}
	}

	@Override
	public void push(E it) {
		// TODO Auto-generated method stub
		top = new Node<E>(it, top);
		size++;
	}

	@Override
	public void puke() {
		// TODO Auto-generated method stub
		top = null;
		size = 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
