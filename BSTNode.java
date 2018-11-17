package BST;

public class BSTNode<E> {

	private E element; // Value for this node

	private BSTNode left; // reference to left child

	private BSTNode right; // reference to right child

	// Constructors

	public BSTNode(E it, BSTNode<E> l, BSTNode<E> r) {
		element = it;
		left = l;
		right = r;

	}

	public BSTNode(E it) {
		element = it;
		left = null;
		right = null;

	}

	// have the appropriate getters and setters

	public BSTNode getLeft() {
		return left;
	}

	public BSTNode setLeft(BSTNode l) {
		left = l;
		return left;
	}

	public BSTNode getRight() {
		return right;
	}

	public BSTNode setRight(BSTNode r) {
		right = r;
		return r;
	}

	public E getElement() {
		return element;
	}

	public E setElement(E it) {
		element = it;
		return element;
	}

	// also have a method to determine if the node is a leaf or has children

	public Boolean isLeaf() {
		return (left == null && right == null);
	}

	public boolean hasLeft() {
		return (left != null);
	}

	public boolean hasRight() {
		return (right != null);
	}

	public String toString() {
		return element.toString();
	}
}