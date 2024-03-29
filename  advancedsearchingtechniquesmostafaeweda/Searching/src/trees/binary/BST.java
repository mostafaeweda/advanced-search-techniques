package trees.binary;

import java.util.Comparator;

public class BST<E, C extends Comparator<E>> {

	/**
	 * The root of the tree
	 */
	protected BinaryNode<E> root;

	/**
	 * The comparator of the tree to choose element location
	 */
	protected C comp;

	/**
	 * The size of the tree
	 */
	protected int size;

	/**
	 * The comparisons done in the last search operation
	 */
	private int comparisons;

	/**
	 * creates an empty binary Search tree
	 * 
	 * @param comp
	 * @see Comparator
	 * @see #comp
	 */
	public BST(C comp) {
		this.comp = comp;
		this.size = 0;
	}

	/**
	 * creates a tree given its root
	 * 
	 * @param root
	 *            the root of the tree
	 * @param comp
	 * @see Comparator
	 * @see #comp
	 */
	public BST(BinaryNode<E> root, C comp) {
		this.comp = comp;
		addRoot(root);
	}

	/**
	 * adds a root to the tree if it doesn't have one; otherwise throws an
	 * exception
	 * 
	 * @param root
	 * @throws IllegalAccessError
	 */
	public void addRoot(BinaryNode<E> root) {
		if (size != 0)
			throw new IllegalAccessError(
					"Not allowed --> There already exist a root");
		this.root = root;
		size = 1;
	}

	/**
	 * balances the subtree rooted at node r
	 * 
	 * @param r
	 *            the start node at which the error in heights is discovered
	 * @return the new root of the balanced subtree
	 */
	protected BinaryNode<E> balance(BinaryNode<E> r) {
		return r;
	}

	/**
	 * @param value
	 *            The value to insert in the tree
	 */
	public void insert(E value) {
		if (size == 0)
			addRoot(new BinaryNode<E>(value));
		else {
			BinaryNode<E> node = new BinaryNode<E>(value);
			root = insertRec(root, node);
			size++;
		}
	}

	private BinaryNode<E> insertRec(BinaryNode<E> r, BinaryNode<E> n) {
		if (r == null)
			return n;
		int comparison = comp.compare(n.element(), r.element());
		if (comparison < 0) { // n.element < r.element
			BinaryNode<E> temp = insertRec(r.left(), n);
			r.setLeft(temp);
			temp.setParent(r);
			r.hl = Math.max(r.left().hl, r.left().hr) + 1;
		} else {
			BinaryNode<E> temp = insertRec(r.right(), n);
			r.setRight(temp);
			temp.setParent(r);
			r.hr = Math.max((r.right()).hl, (r.right()).hr) + 1;
		}
		if (Math.abs(r.hl - r.hr) > 1)
			r = balance(r);
		return r;
	}

	/**
	 * Finds an element on the tree
	 * 
	 * @param element
	 * @return the element if found; null otherwise
	 */
	public E find(E element) {
		if (size == 0)
			return null;
		BinaryNode<E> temp = find(root, element);
		if (temp == null)
			return null;
		else
			return temp.element();
	}

	protected BinaryNode<E> find(BinaryNode<E> root, E element) {
		BinaryNode<E> next = root;
		int comparison = 0;
		comparisons = 0;
		while (next != null) {
			comparisons++;
			comparison = comp.compare(element, next.element());
			if (comparison < 0) {
				// element < node.element --> move left
				next = next.left();
			} else if (comparison > 0) {
				// element > node.element --> move right
				next = next.right();
			} else { // comparison = 0
				return next;
			}
		}
		return null;
	}

	/**
	 * Finds all the elements in the tree that is equal to element
	 * 
	 * @param element
	 * @return an Object array containing the data of the search
	 */
	public Object[] findAll(E element) {
		if (size == 0)
			return null;
		Object[] found = new Object[10];
		BinaryNode<E> temp = find(root, element);
		int counter = 0;
		while (temp != null && comp.compare(element, temp.element()) == 0) {
			if (counter == found.length) {
				Object[] newFound = new Object[2 * found.length];
				for (int i = 0; i < counter; i++)
					newFound[i] = found[i];
				found = newFound;
			}
			found[counter++] = temp.element();
			temp = temp.right();
		}
		Object[] newFound = new Object[counter];
		for (int i = 0; i < counter; i++)
			newFound[i] = found[i];
		found = newFound;
		return found;
	}

	/**
	 * deletes the first occurrence of the specified element from the tree if
	 * found
	 * 
	 * @param element
	 */
	public void delete(E element) {
		root = deleteRec(root, element);
		size--;
	}

	/**
	 * deletes the specified element and all its occurrences in the tree
	 * 
	 * @param element
	 */
	public void deleteAll(E element) {
		int n = findAll(element).length;
		size -= n;
		for (int i = 0; i < n; i++)
			root = deleteRec(root, element);
	}

	protected BinaryNode<E> deleteRec(BinaryNode<E> node, E element) {
		if (node == null)
			return null;
		int comparsion = comp.compare(element, node.element());
		if (comparsion < 0) {
			// element < node.element
			node.setLeft(deleteRec(node.left(), element));
			node.hl = Math.max(node.hasLeft() ? node.left().hl : 0, node
					.hasLeft() ? node.left().hr : 0) + 1;
			if (Math.abs(node.hl - node.hr) > 1)
				node = balance(node);
			return node;
		} else if (comparsion > 0) {
			// element < node.element
			node.setRight(deleteRec(node.right(), element));
			node.hr = Math.max(node.hasRight() ? node.right().hl : 0, node
					.hasRight() ? node.right().hr : 0) + 1;
			if (Math.abs(node.hl - node.hr) > 1)
				node = balance(node);
			return node;
		} else {
			if (!(node.hasLeft() || node.hasLeft())) {
				return null;
			} else if (!node.hasRight()) {
				return node.left();
			} else if (!node.hasLeft()) {
				return node.right();
			} else {
				BinaryNode<E> temp = successor(node);
				node.setElement(temp.element());
				node.setRight(deleteRec(node.right(), temp.element()));
				return node;
			}
		}
	}

	protected BinaryNode<E> successor(BinaryNode<E> node) {
		BinaryNode<E> temp = node.right();
		while (temp.hasLeft())
			temp = temp.left();
		return temp;
	}

	/**
	 * @return the size of the binary search tree
	 */
	public int size() {
		return size;
	}

	/**
	 * @return the number of comparisons for the last search done on the tree
	 */
	public int getComparsons() {
		return comparisons;
	}

	/**
	 * @return a string representation of the tree traversed pre-orderly
	 */
	public String preOrderTraversal() {
		StringBuffer buffer = new StringBuffer();
		preorderHelper(root, buffer);
		return buffer.toString();
	}

	private void preorderHelper(BinaryNode<E> node, StringBuffer buffer) {
		if (node == null)
			return;
		buffer.append(node.element().toString() + " "); // output node data
		preorderHelper(node.left(), buffer); // traverse left subtree
		preorderHelper(node.right(), buffer); // traverse right subtree
	}

	/**
	 * @return a string representation of the tree traversed post-orderly
	 */
	public String postOrderTraversal() {
		StringBuffer buffer = new StringBuffer();
		postOrderHelper(root, buffer);
		return buffer.toString();
	}

	private void postOrderHelper(BinaryNode<E> node, StringBuffer buffer) {
		if (node == null)
			return;
		postOrderHelper(node.left(), buffer); // traverse left subtree
		postOrderHelper(node.right(), buffer); // traverse right subtree
		buffer.append(node.element().toString() + " "); // output node data
	}

	public String inOrderTraversal()
	{
		StringBuffer buffer = new StringBuffer();
		inOrderHelper(root, buffer);
		return buffer.toString();
	}

	/**
	 * Traversed the tree in-orderly
	 */
	private void inOrderHelper(BinaryNode<E> node, StringBuffer buffer)
	{
		if (node == null)
			return;
		inOrderHelper(node.left(), buffer); // traverse left subtree
		buffer.append(node.element().toString() + " "); // output node data
		inOrderHelper(node.right(), buffer); // traverse right subtree
	}

	public String toString()
	{
		StringBuffer buffer = new StringBuffer();
		buffer.append("Pre-order Traverseal\n");
		buffer.append(preOrderTraversal());
		buffer.append("\n\nIn-order Traverseal\n");
		buffer.append(inOrderTraversal());
		buffer.append("\n\nPost-order Traverseal\n");
		buffer.append(postOrderTraversal());
		buffer.append("\n\n Size: ");
		buffer.append(size);
		return buffer.toString();
	}
}
