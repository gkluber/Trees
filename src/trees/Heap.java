package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<? super T>>
{
	private List<T> completeTree;
	private boolean min;

	public Heap(boolean min)
	{
		this.min = min;
		completeTree = new ArrayList<>();
	}

	public Heap(boolean min, int initialCapacity)
	{
		this.min = min;
		completeTree = new ArrayList<>(initialCapacity);
	}

	public T findMin()
	{
		if(!min)
			throw new UnsupportedOperationException("Attempted to find min of max-heap!");

		if(size() == 0)
			throw new NoSuchElementException("Attempted to find min of empty min-heap!");

		return findRoot();
	}

	public T removeMin()
	{
		if(!min)
			throw new UnsupportedOperationException("Attempted to remove min of max-heap!");

		if(size() == 0)
			throw new NoSuchElementException("Attempted to remove min of empty min-heap!");

		T min = completeTree.get(0);
		removeRoot();
		return min;
	}

	public T findMax()
	{
		if(min)
			throw new UnsupportedOperationException("Attempted to find max of min-heap!");

		if(size() == 0)
			throw new NoSuchElementException("Attempted to find max of empty max-heap!");

		return findRoot();
	}

	public T removeMax()
	{
		if(min)
			throw new UnsupportedOperationException("Attempted to remove max of min-heap!");

		if(size() == 0)
			throw new NoSuchElementException("Attempted to remove max of empty max-heap!");

		T max = completeTree.get(0);
		removeRoot();
		return max;
	}

	private void removeRoot()
	{
		int index = 0;
		while(true)
		{
			int leftIndex = index * 2 + 1;
			int rightIndex = index * 2 + 2;
			T value = completeTree.get(index);

			if(leftIndex >= size() && rightIndex >= size())
				break;
			else if(leftIndex >= size())
			{
				T right = completeTree.get(rightIndex);
				if(shouldSwap(value, right))
				{
					swap(rightIndex, index);
					index = rightIndex;
				}
				else
					break;
			}
			else if(rightIndex >= size())
			{
				T left = completeTree.get(leftIndex);
				if(shouldSwap(value, left))
				{
					swap(leftIndex, index);
					index = leftIndex;
				}
				else
					break;
			}
			else
			{
				T left = completeTree.get(leftIndex);
				T right = completeTree.get(rightIndex);
				T candidateMin = TreeUtil.min(left, right);
				if(shouldSwap(value, candidateMin))
					swap(index, candidateMin == left ? leftIndex : rightIndex);
				else
					break;
			}
		}
	}

	private T findRoot()
	{
		return size() == 0 ? null : completeTree.get(0);
	}

	public void insert(T value)
	{
		if(value == null)
			return;

		completeTree.add(value);

		// Now, percolate up if necessary:

		int index = size() - 1;
		while(index != 0)
		{
			int lastIndex = index;
			index = index % 2 == 0 ? index / 2 - 1 : index / 2;
			if(value.compareTo(completeTree.get(index)) < 0)
				swap(index, lastIndex);
			else
				break;
		}
	}

	public boolean isMinHeap()
	{
		return min;
	}

	public int size()
	{
		return completeTree.size();
	}

	private void swap(int index1, int index2)
	{
		T temp = completeTree.get(index1);
		completeTree.set(index1, completeTree.get(index2));
		completeTree.set(index2, temp);
	}

	private boolean shouldSwap(T parent, T child)
	{
		if(min)
			return parent.compareTo(child) > 0;
		else
			return parent.compareTo(child) < 0;
	}
}
