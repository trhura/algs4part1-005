import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.NoSuchElementException;

public class DequeTest {
    @Test
    public void newDequeShouldBeEmpty() {
	Deque<Integer> d = new Deque<Integer>();
	assertEquals(d.size(), 0);
	assertEquals(d.isEmpty(), true);
    }

    @Test
    public void shouldDecreaseSizeWhenAddingItems() {
	Deque<Integer> d = new Deque<Integer>();
	d.addFirst(3);
	d.addFirst(2);
	d.addFirst(1);

	d.removeFirst();
	assertEquals(d.size(), 2);

	d.removeLast();
	assertEquals(d.size(), 1);

	d.removeLast();
	assertEquals(d.size(), 0);
    }

    @Test
    public void shouldIncreaseSizeWhenAddingItems() {
	Deque<Integer> d = new Deque<Integer>();
	d.addFirst(3);
	d.addFirst(4);
	assertEquals(d.size(), 2);
	d.addLast(4);
	assertEquals(d.size(), 3);
    }

    @Test(expected=java.util.NoSuchElementException.class)
    public void removingFirstFromEmptyDequeShouldRaiseException() {
	Deque<Integer> d = new Deque<Integer>();
	d.removeFirst();
    }

    @Test
    public void removingFirstAndLastShouldWork() {
    	Deque<Integer> d = new Deque<Integer>();
    	int first, last, i;

    	d.addFirst(3);
    	d.addFirst(4);
    	first = d.removeFirst();
    	assertEquals(first, 4);
    	last = d.removeLast();
    	assertEquals(last, 3);
    	assertEquals(d.size(), 0);

    	d.addFirst(2);
    	d.addFirst(1);
    	d.addLast(3);
    	d.addLast(4);

    	first = d.removeFirst();
    	assertEquals(first, 1);
    	last = d.removeLast();
    	assertEquals(last, 4);
    	first = d.removeFirst();
    	assertEquals(first, 2);
    	last = d.removeLast();
    	assertEquals(last, 3);
    	assertEquals(d.isEmpty(), true);

    	d.addLast(2);
    	d.addFirst(1);
    	d.addLast(3);
    	d.addLast(4);

    	first = d.removeFirst();
    	assertEquals(first, 1);
    	first = d.removeFirst();
    	assertEquals(first, 2);
    	first = d.removeFirst();
    	assertEquals(first, 3);
    	first = d.removeFirst();
    	assertEquals(first, 4);
    }

    @Test
    public void testIterator (){
    	Deque<Integer> d = new Deque<Integer>();
    	int[] integers = new int[] {1, 2, 3, 4, 5};
    	for (int integer: integers) {
    	    d.addLast(integer);
    	}

    	int i = 0;
    	for (int integer: d) {
    	    assertEquals(integers[i], integer);
    	    i++;
    	}


	int size = d.size();
	for (i = 0; i < size; i++) {
	    int j = d.removeFirst();
	}

	assertEquals(d.isEmpty(),true);

    	d.addLast(2);
    	d.addFirst(1);
    	d.addLast(3);
    	d.addLast(4);
    	d.addLast(5);
	i = 0;
    	for (int integer: d) {
    	    assertEquals(integers[i], integer);
    	    i++;
    	}
    }
}
