package trees.binary;

/**
 * Binary node is the class having the elements and the characteristics of
 * the Tree nodes used in binary search trees and avl trees
 * 
 * @author Mostafa Mahmoud Mahmoud Eweda
 * @version 1.0
 * @since JDK 1.6
 * 
 * @param <E> the element to search for
 */
public class BinaryNode<E> {

	/**
	 * the element of the node --> element saved in the tree
	 */
	protected E element;

	/**
	 * The left child of the node
	 */
	protected BinaryNode<E> left;

	/**
	 * The right child of the node
	 */
	protected BinaryNode<E> right;

	/**
	 * The parent of the node
	 */
	protected BinaryNode<E> parent;

	/**
	 * The left height of the node
	 */
	public int hl = 0;

	/**
	 * The right height of the node
	 */
	public int hr = 0;

	/**
	 * creates a not-attached binary node with an element e
	 * @param element
	 */
	public BinaryNode(E element) {
		this.element = element;
	}

	/**
	 * Creates a binary node with element and sets its parent to a given node
	 * @param element
	 * @param parent
	 */
	public BinaryNode(E element, BinaryNode<E> parent) {
		this(element);
		this.parent = parent;
	}

	/**
	 * @return the parent of the node
	 */
	public BinaryNode<E> parent() {
		return parent;
	}

	/**
	 * 
	 * @param parent
	 */
	public void setParent(BinaryNode<E> parent) {
		this.parent = parent;
	}

	/**
	 * @return the left of the node
	 */
	public BinaryNode<E> left() {
		return left;
	}

	/**
	 * @return the right of the node
	 */
	public BinaryNode<E> right() {
		return right;
	}

	/**
	 * @return true if the node ha a left child; false otherwise
	 */
	public boolean hasLeft() {
		return left != null;
	}

	/**
	 * @return true if the node has right child; false otherwise
	 */
	public boolean hasRight() {
		return right != null;
	}

	/**
	 * sets the left child to left
	 */
	public void setLeft(BinaryNode<E> left) {
		this.left = left;
	}

	/**
	 * sets the eight child to left
	 */
	public void setRight(BinaryNode<E> right) {
		this.right = right;
	}

	/**
	 * @return the element contained in the node
	 */
	public E element() {
		return element;
	}

	/**
	 * sets the element of the node
	 * @param element
	 */
	public void setElement(E element) {
		this.element = element;
	}

	/**
	 * @return true if the node is external node --> has no right or left children
	 */
	public boolean isExternal() {
		return right == null && left == null;
	}

	/**
	 * @return true if the node has right or left children
	 */
	public boolean isInternal() {
		return right != null || left != null;
	}

	public String toString() {
		return element.toString();
	}
}
