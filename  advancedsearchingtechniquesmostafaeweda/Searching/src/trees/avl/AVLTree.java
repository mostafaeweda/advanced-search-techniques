package trees.avl;

import java.util.Comparator;

import trees.binary.BST;
import trees.binary.BinaryNode;

public class AVLTree<E, C extends Comparator<E>> extends BST<E, C> {

	public AVLTree(BinaryNode<E> root, C comp) {
		super(root, comp);
	}

	public AVLTree(C comp) {
		super(comp);
	}

	public BinaryNode<E> balance(BinaryNode<E> r) {
		if (r.hl > r.hr) {
			if (r.left().hr > r.left().hl)
				r.setLeft(rotateLeft(r.left()));
			r = rotateRight(r);
		} else {
			if (r.right().hl > r.right().hr)
				r.setRight(rotateRight(r.right()));
			r = rotateLeft(r);
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
		B.hl = B.hasRight() ? Math.max(B.right().hl, B.right().hr) : 0 + 1;
		B.hr = B.hasLeft() ? Math.max(B.left().hl, B.left().hr) : 0 + 1;
		return B;
	}

	private BinaryNode<E> rotateRight(BinaryNode<E> A) {
		BinaryNode<E> B = A.left();
		A.setLeft(B.right());
		A.hl = B.hr;
		B.setParent(A.parent());
		A.setParent(B);
		B.setRight(A);
		B.hl = Math.max(B.right().hl, B.right().hr) + 1;
		B.hr = Math.max(B.left().hl, B.left().hr) + 1;
		return B;
	}

	@Override
	protected BinaryNode<E> newNode(E value) {
		return new BinaryNode<E>(value);
	}
}