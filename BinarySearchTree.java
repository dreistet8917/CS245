package BST;

public class BinarySearchTree<E extends Comparable<E>> {

	private BSTNode root;

	private int size;

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	public BinarySearchTree(BSTNode node) {
		root = node;
		size = 1;
	}

	/**
	 * searches for a node that contains it.
	 * 
	 * if it finds it, it returns that node
	 * 
	 * else it returns null
	 * 
	 * @param it
	 *            - the element to look for
	 * 
	 * @return the node that contains it
	 * 
	 */

	public BSTNode search(E it) {
		// BSTNode<E> node = new BSTNode<E>(it);
		BSTNode<E> node = root;
		while (node != null) {
			int compareResult = it.compareTo(node.getElement());
			if (compareResult < 0) {
				node = node.getLeft();
			} else if (compareResult > 0) {
				node = node.getRight();
			} else if (compareResult == 0) {
				return node;
			}
		}
		System.out.println(it + "is not in the tree");
		return null;
	}

	/**
	 * determines is the tree contains the element
	 * 
	 * @return true if it is in the tree
	 * 
	 */

	public boolean contains(E it) {
		// BSTNode<E> node = root;
		// while (it != root) {
		// int compareResults = it.compareTo(node.getElement());
		// if (compareResults < 0) {
		// node = root.getLeft();
		// if(node == it) {
		// return true;
		// }
		// } else if (compareResults > 0) {
		// node = root.getRight();
		// if(node == it) {
		// return true;
		// }
		// }
		// }
		// return false;
		return search(it) != null;
	}

	/**
	 * Add the element to the correct location
	 * 
	 * all elements to the left are less than the parent
	 * 
	 * all elements to the rights are greater than the parent
	 * 
	 * Do not allow duplicates
	 * 
	 * @param it
	 *            the element to insert
	 * 
	 */

	public void insert(E it) {
		BSTNode<E> newNode = new BSTNode<E>(it);
		if (root == null) {
			root = newNode;
			return;
		}
		BSTNode<E> parent = null;
		BSTNode<E> node = root;
		while (node != null) {
			parent = node;
			int compareResult = it.compareTo(node.getElement());
			if (compareResult < 0) {
				node = node.getLeft();
			} else if (compareResult > 0) {
				node = node.getRight();
			} else {
				return;
			}
		}
		int res = it.compareTo(parent.getElement());
		if (res < 0) {
			parent.setLeft(newNode);
		} else {
			parent.setRight(newNode);
		}
		size++;
	}

	/**
	 * Removes the node that contains it.
	 * 
	 * If the tree does not contain it, it prints that to
	 * 
	 * the user and does nothing else.
	 * 
	 * Otherwise it removes the node and maintains the
	 * 
	 * BST properties
	 * 
	 * if removing a node with two children, replace it
	 * 
	 * with its inorder predecessor.
	 * 
	 * @param the
	 *            element of the node you want to remove.
	 * 
	 */

	public void remove(E it) {
		BSTNode<E> parent = null;
		BSTNode<E> child = null;
		BSTNode<E> node = root;
		while (node != null && node.getElement() != it) {
			parent = node;
			int compareResult = it.compareTo(node.getElement());
			if (compareResult < 0) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}

		if (node == null) {
			System.out.println("Failed to find " + it + " for removal.");
			return;
		}

		if (node.isLeaf()) {
			if (parent == null) {
				root = null;
			} else if (it.compareTo(parent.getElement()) < 0) {
				parent.setElement(null);
			} else {
				parent.setRight(null);
			}
		} else if (node.getElement() == null) {
			child = node.getRight();
			node.setLeft(child.getLeft());
			node.setRight(child.getRight());
		} else if (node.getRight() == null) {
			child = node.getLeft();
		} else {
			child = node.getLeft();
			parent = null;
			while (child.hasRight()) {
				parent = child;
				child = child.getRight();
			}
			if (parent == null) {
				swapElements(node, child);
				node.setLeft(child.getLeft());
			} else {
				swapElements(node, child);
				parent.setRight(child.getLeft());
			}
		}
		size--;
	}

	/**
	 * Returns the height of the tree
	 * 
	 * if tree is empty, height is -1
	 * 
	 * if tree only has one node, height is 0
	 * 
	 * @return the integer height of the tree
	 *
	 * 
	 * 
	 */

	public int getHeight() {
		int height = -1;
		QueueList<BSTNode> q = new QueueList<BSTNode>();
		if (root == null) {
			return height;
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();
			height++;
			while (nodeCount > 0) {
				BSTNode<E> node = q.dequeue();
				if (node.hasLeft()) {
					q.enqueue(node.getLeft());
				}
				if (node.hasRight()) {
					q.enqueue(node.getRight());
				}
				nodeCount--;
			}
		}
		return height;
	}

	/**
	 * Helper method
	 * 
	 * For removal you need to swap elements of nodes
	 * 
	 * @param node1
	 *            , node2 the nodes whose contents you are swapping
	 * 
	 */

	private void swapElements(BSTNode node1, BSTNode node2) {
		Object element1 = node1.getElement();
		Object element2 = node2.getElement();
		node1.setElement(element2);
		node2.setElement(element1);
	}

	/**
	 * prints each level of the tree on its own line
	 * 
	 * use your Queue class
	 * 
	 */

	public void printLevelOrder() {
		QueueList<BSTNode> q = new QueueList<BSTNode>();
		if (root == null) {
			System.out.println("There is no data in this tree");
		}
		q.enqueue(root);
		while (!q.isEmpty()) {
			int nodeCount = q.size();
			while (nodeCount > 0) {
				BSTNode<E> node = q.dequeue();
				System.out.print(node.toString() + " ");
				if (node.hasLeft()) {
					q.enqueue(node.getLeft());
				}
				if (node.hasRight()) {
					q.enqueue(node.getRight());
				}
				nodeCount--;
			}
			System.out.println();
		}

	}

	/**
	 * prints the tree in a depth-first fashion
	 * 
	 * use your Stack class
	 * 
	 */

	public void printByDepth() {
		StackList<BSTNode> s = new StackList<BSTNode>();
		if (root == null) {
			System.out.println("There is no data in this tree");
		}
		s.push(root);
		while (!s.isEmpty()) {
			int nodeCount = s.size();
			while (nodeCount > 0) {
				BSTNode<E> node = s.pop();
				System.out.println(node.toString());
				if (node.hasRight()) {
					s.push(node.getRight());
				}
				if (node.hasLeft()) {
					s.push(node.getLeft());
				}
				nodeCount--;
			}
		}
	}

	/**
	 * prints the tree in an inorder fashion.
	 * 
	 * uses a stack to push left children onto the stack
	 * 
	 */

	public void printInOrder() {
		if (root == null) {
			System.out.println("There is no data in this tree");
		}
		StackList<BSTNode> s = new StackList<BSTNode>();
		BSTNode node = root;
		pushLeft(s, node);
		while (!s.isEmpty()) {
				BSTNode last = s.pop();
				System.out.println(last.getElement());
				last = last.getRight();
				pushLeft(s, last);
		}
	}
	
	public void pushLeft(StackList s, BSTNode node) {
		while(node != null) {
			s.push(node);
			node = node.getLeft();
		}
	}
}
