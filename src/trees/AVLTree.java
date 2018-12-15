package trees;

public class AVLTree
{

	public static class AVLNode<T extends Comparable<? super T>> extends BSTNode<T>
	{
		// Order statistic for the height difference between the two children
		private int height;

		public AVLNode(T value)
		{
			this(value, null, null, null);
		}

		public AVLNode(T value, AVLNode<T> left, AVLNode<T> right, AVLNode<T> parent)
		{
			this(value, left, right, parent, 0);
		}

		public AVLNode(T value, AVLNode<T> left, AVLNode<T> right, AVLNode<T> parent, int height)
		{
			super(value, left, right, parent);
			this.height = height;
		}

		public AVLNode<T> getLeft()
		{
			return (AVLNode<T>) left;
		}

		public AVLNode<T> getRight()
		{
			return (AVLNode<T>) right;
		}

		public AVLNode<T> getParent()
		{
			return (AVLNode<T>) parent;
		}

		public int getHeight()
		{
			return height;
		}

		public void setHeight(int height)
		{
			this.height = height;
		}
	}
}
