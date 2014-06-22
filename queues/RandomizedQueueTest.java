import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.util.NoSuchElementException;

public class RandomizedQueueTest {
    @Test
    public void newQueueShouldBeEmpty() {
	RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
	assertEquals(d.size(), 0);
	assertEquals(d.isEmpty(), true);
    }

    @Test
    public void AddShouldIncreaseSize() {
	RandomizedQueue<Integer> d = new RandomizedQueue<Integer>();
	for (int i = 1; i <= 10000; i++) {
	    d.enqueue(i);
	    assertEquals(d.size(), i);
	}

	for (int i = 10000; i < 0; i--) {
	    d.dequeue();
	    assertEquals(d.size(), i);
	}
    }

}
