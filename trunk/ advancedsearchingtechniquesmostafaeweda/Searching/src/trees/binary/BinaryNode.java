package trees.binary;


public class BinaryNode<E> {

	protected E element;
	protected BinaryNode<E> left;
	protected BinaryNode<E> right;
	protected BinaryNode<E> parent;
	public int hl = 0;
	public int hr = 0;

	public BinaryNode(E element) {
		this.element = element;
	}

	public BinaryNode(E element, BinaryNode<E> parent) {
		this(element);
		this.parent = parent;
	}

	public BinaryNode<E> parent() {
		return parent;
	}

	public void setParent(BinaryNode<E> parent) {
		this.parent = parent;
	}

	public BinaryNode<E> left() {
		return left;
	}
	
	public BinaryNode<E> right() {
		return right;
	}
	
	public boolean hasLeft() {
		return left != null;
	}
	
	public boolean hasRight() {
		return right != null;
	}
	
	public void setLeft(BinaryNode<E> left) {
		this.left = left;
	}
	
	public void setRight(BinaryNode<E> right) {
		this.right = right;
	}

	public E element() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public boolean isExternal() {
		return right == null && left == null;
	}

	public String toString() {
		return element.toString();
	}
}
