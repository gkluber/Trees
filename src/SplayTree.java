public class SplayTree<T extends Comparable<? super T>>
{
	private BSTNode<T> root;

	/**
	 * Bottom-up implementation of the splay operation. This
	 * is the fundamental operation that underlies all of the
	 * other splay tree methods.
	 *
	 * @param value		value to splay
	 * @return			true if the value was successfully splayed (i.e.,
	 * 					the splay tree contained the element); otherwise,
	 * 					false.
	 */
	public boolean bottomUpSplay(T value)
	{

	}

	/**
	 * Top-down implementation of the splay operation. This
	 * operation is more advanced and complex than the
	 * bottom-up splay, but it is slightly faster on average.
	 *
	 * @param value		value to splay
	 */
	public boolean topDownSplay(T value)
	{

	}

	/**
	 * Attempts to insert an element into the splay tree. Has
	 * amortized cost of O(logn)
	 *
	 * @param value		value to insert
	 * @return			true if the insertion was successful (i.e,
	 * 					the tree did not already contain the value);
	 * 					otherwise, false.
	 */
	public boolean insert(T value)
	{

		return false;
	}

	/**
	 * Checks if an element is a member of this splay tree.
	 * Has amortized cost of O(logn)
	 *
	 * @param value		value to test
	 * @return			true if the splay tree contains value;
	 * 					false otherwise
	 */
	public boolean contains(T value)
	{
		return topDownSplay(value);
	}
}
