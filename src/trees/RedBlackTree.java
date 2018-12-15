package trees;

public class RedBlackTree<T extends Comparable<? super T>>
{


	public static class RedBlackNode<T extends Comparable<? super T>> extends BSTNode<T>
	{
		private boolean red;

		public RedBlackNode(T value)
		{
			this(value, null, null, null);
		}

		public RedBlackNode(T value, RedBlackNode<T> left, RedBlackNode<T> right, RedBlackNode<T> parent)
		{
			super(value, left, right, parent);
			red = true;
		}

		public RedBlackNode<T> getLeft()
		{
			return (RedBlackNode) left;
		}

		public RedBlackNode<T> getRight()
		{
			return (RedBlackNode) right;
		}

		public boolean isRed()
		{
			return red;
		}
	}
}
