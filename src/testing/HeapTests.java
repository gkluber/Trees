package testing;

import org.junit.jupiter.api.Test;
import trees.Heap;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HeapTests
{
	@Test
	public void testRemoveEmptyHeap()
	{
		final Heap<Integer> minHeap = new Heap<>(true);
		assertThrows(NoSuchElementException.class, () -> minHeap.removeMin());
		assertThrows(NoSuchElementException.class, () -> minHeap.findMin());

		final Heap<Integer> maxHeap = new Heap<>(false);
		assertThrows(NoSuchElementException.class, () -> maxHeap.removeMax());
		assertThrows(NoSuchElementException.class, () -> maxHeap.findMax());
	}

	@Test
	public void testMinMaxIllegalOperators()
	{
		final Heap<Integer> minHeap = new Heap<>(true);
		assertThrows(UnsupportedOperationException.class, () -> minHeap.removeMax());
		assertThrows(UnsupportedOperationException.class, () -> minHeap.findMax());

		final Heap<Integer> maxHeap = new Heap<>(false);
		assertThrows(UnsupportedOperationException.class, () -> maxHeap.removeMin());
		assertThrows(UnsupportedOperationException.class, () -> maxHeap.removeMin());
	}

	@Test
	public void testHeapSingleton()
	{
		Heap<Integer> heap = new Heap<>(true);
		heap.insert(3);
		assertEquals(3, heap.removeMin().intValue());
	}

	@Test
	public void testRandomHeapRemove()
	{
		final int numTrials = 1000;
		final int maxSize = 100;

		Random random = new Random();
		for(int i = 0; i < numTrials; i++)
		{
			List<Integer> list = new ArrayList<>();
			int size = random.nextInt(maxSize) + 1;
			for(int j = 0; j < size; j++)
				list.add(random.nextInt());

			List<Integer> expected = new ArrayList<>(list);
			Collections.sort(expected);

			Heap<Integer> minHeap = new Heap<>(true);
			for(int element : list)
				minHeap.insert(element);

			List<Integer> result = new ArrayList<>();
			while(minHeap.size() != 0)
				result.add(minHeap.removeMin());

			assertEquals(expected, result, "For sequence " + list + ", the heap gave the wrong values!" +
														"\nActual: " + result);
		}
	}

	@Test
	public void testRandomHeapFind()
	{
		final int numTrials = 10000;
		final int maxSize = 100;

		Random random = new Random();
		for(int i = 0; i < numTrials; i++)
		{
			List<Integer> list = new ArrayList<>();
			int size = random.nextInt(maxSize) + 1;
			for(int j = 0; j < size; j++)
				list.add(random.nextInt());

			int min = Collections.min(list);

			Heap<Integer> minHeap = new Heap<>(true);
			for(int element : list)
				minHeap.insert(element);

			int result = minHeap.findMin();
			assertEquals(min, result, "For sequence " + list + ", the heap gave the wrong minimum!" +
					"\nExpected: " + min + "" +
					"\nActual: " + result);
		}
	}
}
