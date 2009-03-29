package trees.avl;

import java.util.Comparator;

import list.AbstractList;
import list.UnsortedList;

import trees.binary.BST;
import trees.binary.BinaryNode;

public class AVLTree<E, C extends Comparator<E>> extends BST<E, C> {

	public AVLTree(BinaryNode<E> root, C comp) {
		super(root, comp);
	}

	public AVLTree(C comp) {
		super(comp);
	}

	public Object[] findAll(E element) {
		if (size == 0)
			return null;
		UnsortedList<BinaryNode<E>> list = new UnsortedList<BinaryNode<E>>(10);
		BinaryNode<E> temp = find(root, element);
		findAll(list, temp, element);
		Object[] found = new Object[list.size()];
		for (int i = 0; i < found.length; i++) {
			found[i] = list.get(i).element();
		}
		return found;
	}

	private void findAll(AbstractList<BinaryNode<E>> list, BinaryNode<E> temp,
			E element) {
		if (temp == null)
			return;
		if (comp.compare(temp.element(), element) == 0) {
			list.add(temp);
		}
		findAll(list, temp.right(), element);
		findAll(list, temp.left(), element);
	}

	public BinaryNode<E> balance(BinaryNode<E> r) {
		if (r.hl > r.hr) {
			if (r.left().hr > r.left().hl) {
				BinaryNode<E> node = rotateLeft(r.left());
				node.setParent(r);
				r.setLeft(node);
			}
			BinaryNode<E> temp = r.parent();
			r = rotateRight(r);
			r.setParent(temp);
		} else {
			if (r.right().hl > r.right().hr) {
				BinaryNode<E> node = rotateRight(r.right());
				node.setParent(r);
				r.setRight(node);
			}
			BinaryNode<E> temp = r.parent();
			r = rotateLeft(r);
			r.setParent(temp);
		}
		return r;
	}

	private BinaryNode<E> rotateLeft(BinaryNode<E> A) {
		BinaryNode<E> B = A.right();
		A.setRight(B.left());
		A.hr = B.hl;
		B.setParent(A.parent());
		A.setParent(B);
		B.setLeft(A);
		// B.hr = Math.max(B.right().hl, B.right().hr) + 1;
		// B.hl = Math.max(A.hl, A.hr) + 1; 
		B.hr = B.hasRight() ? Math.max(B.right().hl, B.right().hr) + 1 : 0;
		B.hl = B.hasLeft() ? Math.max(B.left().hl, B.left().hr) + 1 : 0;
		return B;
	}

	private BinaryNode<E> rotateRight(BinaryNode<E> A) {
		BinaryNode<E> B = A.left();
		A.setLeft(B.right());
		A.hl = B.hr;
		B.setParent(A.parent());
		A.setParent(B);
		B.setRight(A);
		// B.hr = Math.max(A.hl, A.hr) + 1;
		// B.hl = Math.max(B.left().hl, B.left().hr) + 1;
		B.hr = B.hasRight() ? Math.max(B.right().hl, B.right().hr) + 1 : 0;
		if (B.hasLeft())
			B.hl = Math.max(B.left().hl, B.left().hr) + 1;
		else
			B.hl = 0;
		return B;
	}
}