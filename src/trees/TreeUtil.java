package trees;

public class TreeUtil
{
	public static <T extends Comparable<? super T>> BSTNode<T> rotateLeft()
	{
		//todo
		return null;
	}

	public static <T extends Comparable<? super T>> BSTNode<T> rotateRight()
	{
		//todo
		return null;
	}

	public static <T extends Comparable<? super T>> boolean isBST(BSTNode<T> parent)
	{
		// choose arbitrary value for min since it doesn't affect behavior
		return (Boolean) isBSTHelper(parent, true)[0];
	}

	/**
	 * TODO create tuple record instead of Object[] and fix documentation
	 *
	 * Helper method that checks if a TreapMap, described though ImmutableTreapNode, is a binary search tree.
	 * Works by traversing the BST depth-first, then propagating results back up the tree. In particular, if
	 * a TreapMap has a subtreap that is not a BST, then it cannot be a BST.
	 *
	 * @param parent	the node to check the BST property on
	 * @param min		true if the method will return the minimum value;
	 *                  otherwise, the method will return the maximum value
	 * @return			a pair of values:
	 * 						true if the subtreaps is indeed a BST;
	 * 						otherwise, false.
	 * 						and, the minimum or maximum value, depending on the boolean
	 * 						min passed to the method
	 */
	public static <T extends Comparable<? super T>> Object[] isBSTHelper(BSTNode<T> parent, boolean min)
	{
		if(parent == null)
			return new Object[]{true, null};

		Object[] leftSubtree = isBSTHelper(parent.getLeft(), true);
		Object[] rightSubtree = isBSTHelper(parent.getRight(), false);
		boolean leftBST = (Boolean) leftSubtree[0];
		boolean rightBST = (Boolean) rightSubtree[0];

		if(!leftBST || !rightBST)
			return new Object[]{false, null};

		T parentValue = parent.getValue();
		T leftValue = (T) leftSubtree[1];
		T rightValue = (T) rightSubtree[1];

		if(leftValue == null && rightValue == null)
			return new Object[]{true, parentValue};
		else if(leftValue == null)
		{
			return new Object[]{parentValue.compareTo(rightValue) < 0, min ? parentValue : rightValue};
		}

		else if(rightValue == null)
		{
			return new Object[]{parentValue.compareTo(leftValue) > 0, min ? leftValue : parentValue};
		}
		else
		{
			if(parentValue.compareTo(rightValue) >= 0
					|| parentValue.compareTo(leftValue) <= 0)
				return new Object[]{false, null}; // this means that the tree is not BST

			return new Object[]{true, min ? leftValue : rightValue};
		}
	}

	/**
	 * Recursively determines if the TreapMap described by the ImmutableTreapNode class is a heap.
	 * Specifically, this checks if each node has a priority greater than the priority of its children.
	 *
	 * @param parent	root of the treap
	 * @return			true if the treap is a heap;
	 * 					otherwise, false
	 */
	public static <T extends Comparable<? super T>> boolean isHeap(BSTNode<T> parent)
	{
		BSTNode<T> leftChild = parent.getLeft();
		BSTNode<T> rightChild = parent.getRight();
		boolean isLocallyHeap = (leftChild == null || leftChild.getValue().compareTo(parent.getValue()) <= 0)
				&& (rightChild == null || rightChild.getValue().compareTo(parent.getValue()) <= 0);
		boolean leftHeap = leftChild == null || isHeap(leftChild);
		boolean rightHeap = rightChild == null || isHeap(rightChild);
		return isLocallyHeap && leftHeap && rightHeap;
	}

	public static <T extends Comparable<? super T>> boolean isRedBlack(RedBlackTree.RedBlackNode<T> root)
	{
		if(root.isRed())
			return false;

		if(!isBST(root))
			return false;

		// Check that the black heights are uniform.
		int blackHeight = getBlackHeight(root);
		if(blackHeight == -1)
			return false;

		// Check that red nodes only have black children
		if(hasColorConflicts(root))
			return false;

		return true;
	}

	public static <T extends Comparable<? super T>> boolean hasColorConflicts(RedBlackTree.RedBlackNode<T> node)
	{
		if(node == null)
			return false;

		RedBlackTree.RedBlackNode<T> left = node.getLeft();
		RedBlackTree.RedBlackNode<T> right = node.getRight();
		boolean leftConflicts = hasColorConflicts(left);
		boolean rightConflicts = hasColorConflicts(right);
		if(leftConflicts || rightConflicts)
			return true;

		return node.isRed() &&
				((left != null && left.isRed()) || (right != null && right.isRed()));
	}

	/**
	 * todo documentation. returns -1 when imbalance detected
	 *
	 * @param node
	 * @param <T>
	 * @return
	 */
	public static <T extends Comparable<? super T>> int getBlackHeight(RedBlackTree.RedBlackNode<T> node)
	{
		if(node == null || node.getLeft() == null && node.getRight() == null)
			return 0;

		int leftHeight = getBlackHeight(node.getLeft());
		int rightHeight = getBlackHeight(node.getRight());
		if(leftHeight != rightHeight)
			return -1;

		return leftHeight + (node.isRed() ? 0 : 1);
	}

	public static <T extends Comparable<? super T>> boolean isAVL(AVLTree.AVLNode<T> root)
	{
		return true; //todo
	}

	public static <T extends Comparable<? super T>> Heap<T> heapify(BSTNode<T> tree)
	{
		return null; //todo
	}

	//todo documentation
	public static <T extends Comparable<? super T>> int getDepth(BSTNode<T> node, BSTNode<T> root)
	{
		if(node == null || node.getValue() == null)
			return -1;

		T value = node.getValue();
		int depth = 0;
		while(true)
		{
			// We could not find the supplied node in the tree
			if(root == null)
				return -1;

			int comparison = value.compareTo(root.getValue());
			if(comparison > 0)
				root = root.getLeft();
			else
			{
				if(root == node)
					return depth;

				root = root.getRight();
			}
			++depth;
		}
	}

	public static <T extends Comparable<? super T>> T min(T t1, T t2)
	{
		return t1.compareTo(t2) <= 0 ? t1 : t2;
	}

	public static <T extends Comparable<? super T>> T max(T t1, T t2)
	{
		return t1.compareTo(t2) >= 0 ? t1 : t2;
	}

	// swaps the values
	public static <T extends Comparable<? super T>> void swap(BSTNode<T> node1, BSTNode<T> node2)
	{
		T temp = node1.getValue();
		node1.setValue(node2.getValue());
		node2.setValue(temp);
	}
}
