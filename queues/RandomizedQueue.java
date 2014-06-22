import java.util.NoSuchElementException;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item>{
    private int capacity = 16;
    private Item[] items;
    private int size;

    public RandomizedQueue() {
	// construct an empty randomized queue
	items = (Item[]) new Object[capacity];
    }

    private RandomizedQueue(RandomizedQueue queue) {
	this.size = queue.size();
	this.capacity = queue.capacity;
	items = (Item[]) new Object[capacity];

	for(int i = 0; i < queue.size(); i++) {
	    items[i] = (Item) queue.items[i];
	}
    }

    public boolean isEmpty() {
	return size == 0;
    }

    public int size() {
	return size;
    }

    public void enqueue(Item item) {
	// add the item
	if (item == null) throw new NullPointerException();
	add(item);
    }

    public Item dequeue() {
	// delete and return a random item
	if (isEmpty()) throw new NoSuchElementException();

	int index = getRandomIndex();
	Item item = items[index];
	removeAt(index);

	return item;
    }

    public Item sample() {
	// return (but do not delete) a random item
	if (isEmpty()) throw new NoSuchElementException();
	return items[getRandomIndex()];
    }

    private int getRandomIndex () {
	return StdRandom.uniform(size);
    }

    public Iterator<Item> iterator() {
	// return an independent iterator over items in random order
	return new RandomizedIterator(this);
    }

    private class RandomizedIterator implements Iterator {
	private RandomizedQueue queue;

	public RandomizedIterator(RandomizedQueue queue) {
	    this.queue = new RandomizedQueue(queue);
	}

	public boolean hasNext() {
	    return !queue.isEmpty();
	}

	public Item next() {
	    if (queue.isEmpty()) throw new NoSuchElementException();
	    return (Item) queue.dequeue();
	}

	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }

    private void removeAt (int index) {
	size--;
	items[index] = items[size];
	items[size] = null;

	if (size < capacity/4) {
	    changeCapacity(capacity/4);
	}
    }

    private void changeCapacity (int capacity) {
	Item[] olditems = items;
	items = (Item[]) new Object[capacity];
	for (int i = 0; i < this.size; i++) {
	    items[i] = olditems[i];
	}

	this.capacity = capacity;
    }

    private void add(Item item) {
	if (size == capacity) {
	    changeCapacity(capacity*2);
	}

	items[size] = item;
	size++;
    }
}
