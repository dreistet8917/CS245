package lab08;

public class SLList<E> implements List<E> {
	private Node<E> head;
	int size = 0;

	public SLList(E item) {
		Node<E> newHead = new Node<E>(item);
		head = newHead;
		size++;
	}

	public SLList() {
		head = null;
		size = 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		head = null;
		size = 0;
	}

	@Override
	public void insert(int index, E item) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}
		if (index == 0) {
			addFirst(item);
		} else {
			Node<E> node = getNode(index - 1);
			addAfter(node, item);
		}
	}

	private Node<E> getNode(int index) {
		// TODO Auto-generated method stub
		Node<E> node = head;
		for (int i = 0; i < index && node != null; i++) {
			node = node.getNext();
		}
		return node;
	}

	private void addAfter(Node<E> node, E item) {
		// TODO Auto-generated method stub
		node.setNext(new Node<E>(item, node.getNext()));
		size++;
	}

	private void addFirst(E item) {
		// TODO Auto-generated method stub
		Node<E> temp = new Node<E>(item, head);
		head = temp;
		size++;
	}

	@Override
	public void add(E item) {
		// TODO Auto-generated method stub
		insert(size, item);
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		if (index == 0) {
			removeFirst();
		} else {
			removeAfter(getNode(index - 1));
		}
	}

	private void removeAfter(Node<E> node) {
		// TODO Auto-generated method stub
		Node<E> temp = node.getNext();
		if (temp != null) {
			node.setNext(temp.getNext());
			size--;
		}
	}

	private void removeFirst() {
		// TODO Auto-generated method stub
		if (head != null) {
			head = head.getNext();
			size--;
		}
	}

	@Override
	public E prev(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}

		if (index == 0) {
			return null;
		}
		return getNode(index - 1).getElement();

	}

	@Override
	public E next(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}

		if (index == size - 1) {
			return null;
		}
		return getNode(index + 1).getElement();
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public void reverse() {
		// TODO Auto-generated method stub
		Node<E> node = (Node<E>) head;
		if (node == null || node.getNext() == null) {
			return;
		}
		Node<E> prev = node.getNext();
		Node<E> curr = prev.getNext();
		prev.setNext(node);
		node.setNext(null);

		while (curr != null) {
			Node<E> next = curr.getNext();
			curr.setNext(prev);
			prev = curr;
			curr = next;
		}
		head = prev;
	}

	@Override
	public E getValue(int index) {
		// TODO Auto-generated method stub
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException(Integer.toString(index));
		}

		Node<E> node = getNode(index);
		return node.getElement();
	}

	/**
	 * inserts the given list after the given index
	 */
	public void insertList(SLList list, int index) {
		if (list.getHead() != null) {
			Node<E> n = getNode(index);
			Node<E> temp = n.getNext();
			n.setNext(list.getHead());
			list.getLast().setNext(temp);
			size += list.length();
		}
	}

	public Node<E> getHead() {
		return head;
	}

	public Node<E> getLast() {
		return getNode(size - 1);
	}

	public String toString() {
		Node<E> nodeRef = head;
		String result = "";
		while (nodeRef != null) {
			result = result + nodeRef.getElement().toString();
			if (nodeRef.getNext() != null) {
				result = result + " ==> ";
			}
			nodeRef = nodeRef.getNext();
		}
		return result;
	}

}
