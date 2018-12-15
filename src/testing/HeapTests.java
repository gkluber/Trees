package testing;

import org.junit.jupiter.api.Test;
import trees.Heap;

import java.util.NoSuchElementException;

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
}
