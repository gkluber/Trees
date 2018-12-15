package trees;

public class BinarySearchTree<T extends Comparable<? super T>>
{
	protected BSTNode<T> root;

	/**
	 * Finds the node that corresponds to this value.
	 *
	 * @param value		value to search for
	 * @return			null if the BST is empty or value is null;
	 * 					the node that would be the parent of the node
	 * 					with the supplied value if the value is not
	 * 					in the BST;
	 * 					otherwise, the node associated with value.
	 */
	public BSTNode<T> find(T value)
	{
		if(root == null || value == null)
			return null;

		BSTNode<T> parent = root;
		while(true)
		{
			int comparison = value.compareTo(parent.getValue());
			if(comparison <= 0)
			{
				if(parent.getLeft() != null)
					parent = parent.getLeft();
				else
					break;
			}
			else
			{
				if(parent.getRight() != null)
					parent = parent.getRight();
				else
					break;
			}
		}

		return parent;
	}

	/**
	 * Check if the BST contains a value. This is functionally equivalent to
	 * checking if find(value) returns the expected value.
	 *
	 * @param value		value
	 * @return			true if the BST contains the value;
	 * 					otherwise, false
	 */
	public boolean contains(T value)
	{
		return find(value).getValue().compareTo(value) == 0;
	}

	/**
	 * Inserts a value into the BST
	 *
	 * Note: if a duplicate value is added to the BST, then the
	 * node will be added on the left-side of a node.
	 *
	 * @param value		value to insert
	 * @return 			true
	 */
	public boolean insert(T value)
	{
		if(root == null)
		{
			root = new BSTNode<>(value);
			return true;
		}

		BSTNode<T> parent = root;
		while(true)
		{
			int comparison = value.compareTo(parent.getValue());
			if(comparison <= 0)
			{
				if(parent.getLeft() != null)
					parent = parent.getLeft();
				else
				{
					parent.setLeft(new BSTNode<>(value));
					return true;
				}
			}
			else
			{
				if(parent.getRight() != null)
					parent = parent.getRight();
				else
				{
					parent.setRight(new BSTNode<>(value));
					return true;
				}
			}
		}
	}

	protected void insert(BSTNode<T> node)
	{
		if(root == null)
		{
			root = node;
			return;
		}

		BSTNode<T> parent = root;
		T value = node.getValue();
		while(true)
		{
			int comparison = value.compareTo(parent.getValue());
			if(comparison <= 0)
			{
				if(parent.getLeft() != null)
					parent = parent.getLeft();
				else
				{
					parent.setLeft(node);
					return;
				}
			}
			else
			{
				if(parent.getRight() != null)
					parent = parent.getRight();
				else
				{
					parent.setRight(node);
					return;
				}
			}
		}
	}

	/**
	 * Attempts to remove value from the BST.
	 *
	 * @param value		value to remove
	 * @return			true if the value was successfully removed (i.e.,
	 * 					the value was in the BST); otherwise, false
	 */
	public boolean remove(T value)
	{
		if(root == null)
			return false;

		BSTNode<T> candidate = find(value);
		if(candidate.getValue().compareTo(value) != 0)
			return false;

		// Now that we have confirmed candidate is the node we want to
		// remove, remove it.
		remove(candidate);
		return true;
	}

	/**
	 * Removes a node from the BST.
	 *
	 * @param node		node to remove
	 * @throws			IllegalArgumentException if the node passed has invalid properties
	 * 					(i.e., it is not a child of its parent or it is not a member of
	 * 					this BST)
	 */
	private void remove(BSTNode<T> node)
	{
		if(node == root)
		{
			root = null;
			return;
		}

		if(node.getLeft() != null)
		{
			T max = removeMax(node.getLeft());
			node.setValue(max);
		}
		else if(node.getRight() != null)
		{
			T min = removeMin(node.getRight());
			node.setValue(min);
		}
		else
		{
			BSTNode<T> parent = node.getParent();

			// If the parent is null, then the node must be a root. But, it
			// is not the root of this tree because the method would have
			// returned initially.
			if(parent == null)
				throw new IllegalArgumentException("Node passed is not in the tree!");

			if(parent.getLeft() == node)
				parent.setLeft(null);
			else if(parent.getRight() == node)
				parent.setRight(null);
			else
				throw new IllegalArgumentException("Node is a not a child of parent!");
		}
	}

	/**
	 * Finds the minimum value in the BST.
	 *
	 * @return	null if the tree is empty;
	 * 			otherwise, the former minimum value
	 *
	 */
	public T findMin()
	{
		if(root == null)
			return null;

		BSTNode<T> parent = root;

		while(parent.getLeft() != null)
			parent = parent.getLeft();

		return parent.getValue();
	}

	/**
	 * Finds the maximum value in the BST.
	 *
	 * @return	null if the tree is empty;
	 * 			otherwise, the former maximum value
	 */
	public T findMax()
	{
		if(root == null)
			return null;

		BSTNode<T> parent = root;

		while(parent.getRight() != null)
			parent = parent.getRight();

		return parent.getValue();
	}

	/**
	 * Removes the minimum value in the BST.
	 *
	 * @return	the former minimum value
	 */
	public T removeMin()
	{
		return removeMin(root);
	}

	/**
	 * Removes the maximum value in the BST.
	 *
	 * @return	the former maximum value
	 */
	public T removeMax()
	{
		return removeMin(root);
	}

	/**
	 * Removes the minimum value in the subtree defined as parent
	 * and all of its descendants.
	 *
	 * @param parent	the root of the subtree to remove the
	 *                  minimum value from
	 * @return			the former minimum value
	 * @throws 			IllegalArgumentException if the node has invalid
	 * 					properties (i.e., it is not a member of the tree)
	 */
	public T removeMin(BSTNode<T> parent)
	{
		if(parent == null)
			return null;

		// We have found the minimum element
		BSTNode<T> leftChild = parent.getLeft();
		BSTNode<T> rightChild = parent.getLeft();
		BSTNode<T> grandparent = parent.getParent();
		if(leftChild == null)
		{
			if(rightChild != null)
			{
				// Replace the value at the parent with the minimum value in the right subtree
				T replacement = removeMin(rightChild);
				parent.setValue(replacement);
				return replacement;
			}
			else
			{
				if(grandparent == null)
				{
					// The root is the minimum and only element
					if(parent != root)
						throw new IllegalArgumentException("Node passed is not in the tree!");
					root = null;
				}
				else
				{
					// The node has neither a left child or a right child, so delete it
					grandparent.setLeft(null);
				}
				return parent.getValue();
			}
		}
		else
		{
			return removeMin(parent.getLeft());
		}
	}

	/**
	 * Removes the maximum value in the subtree defined as parent
	 * and all of its descendants.
	 *
	 * @param parent	the root of the subtree to remove the
	 *                  maximum value from
	 * @return			the former maximum value
	 * @throws 			IllegalArgumentException if the node has invalid
	 * 					properties (i.e., it is not a member of the tree)
	 */
	public T removeMax(BSTNode<T> parent)
	{
		if(parent == null)
			return null;

		// We have found the minimum element
		BSTNode<T> leftChild = parent.getLeft();
		BSTNode<T> rightChild = parent.getLeft();
		BSTNode<T> grandparent = parent.getParent();
		if(rightChild == null)
		{
			if(leftChild != null)
			{
				// Replace the value at the parent with the minimum value in the right subtree
				T replacement = removeMax(leftChild);
				parent.setValue(replacement);
				return replacement;
			}
			else
			{
				if(grandparent == null)
				{
					// The root is the minimum and only element
					if(parent != root)
						throw new IllegalArgumentException("Node passed is not in the tree!");
					root = null;
				}
				else
				{
					// The node has neither a left child or a right child, so delete it
					grandparent.setRight(null);
				}
				return parent.getValue();
			}
		}
		else
		{
			return removeMax(parent.getRight());
		}
	}

	/**
	 * Determines if the tree is empty
	 *
	 * @return 	true if the tree is empty (i.e., it contains
	 * 			no elements); otherwise, false
	 */
	public boolean isEmpty()
	{
		return root == null;
	}
}
