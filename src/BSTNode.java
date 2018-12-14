public class BSTNode<T extends Comparable<? super T>>
{
	protected BSTNode<T> parent;
	protected BSTNode<T> left;
	protected BSTNode<T> right;
	protected T value;

	public BSTNode(T value)
	{
		this.value = value;
	}

	public BSTNode(T value, BSTNode<T> left, BSTNode<T> right, BSTNode<T> parent)
	{
		this.left = left;
		this.right = right;
		this.value = value;
		this.parent = parent;
	}

	public BSTNode<T> getLeft()
	{
		return left;
	}

	public void setLeft(BSTNode<T> left)
	{
		if(this.left != null)
			this.left.setParent(null);
		this.left = left;
		if(left != null)
			left.setParent(this);
	}

	public BSTNode<T> getRight()
	{
		return right;
	}

	public void setRight(BSTNode<T> right)
	{
		if(this.right != null)
			this.right.setParent(null);
		this.right = right;
		if(right != null)
			right.setParent(this);
	}

	public T getValue()
	{
		return value;
	}

	public void setValue(T value)
	{
		this.value = value;
	}

	public BSTNode<T> getParent()
	{
		return parent;
	}

	private void setParent(BSTNode<T> parent)
	{
		this.parent = parent;
	}
}
