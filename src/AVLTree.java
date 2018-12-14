public class AVLTree
{

	public static class AVLNode<T extends Comparable<? super T>> extends BSTNode<T>
	{
		// Order statistic for the height difference between the two children
		private int heightDifference;

		public AVLNode(T value)
		{
			this(value, null, null);
		}

		public AVLNode(T value, AVLNode left, AVLNode right)
		{
			super(value, left, right);
		}

		public AVLNode<T> getLeft()
		{
			return (AVLNode<T>) left;
		}

		public AVLNode<T> getRight()
		{
			return (AVLNode<T>) right;
		}
	}
}
