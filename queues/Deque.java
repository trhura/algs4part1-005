import java.util.NoSuchElementException;
import java.util.Iterator;

public class Deque<Item> implements Iterable<Item>{
    private class Node {
	Item item;
	Node next;
	Node prev;

	protected Node(Item item) {
	    this.item = item;
	}

	protected Node(Item item, Node next, Node prev) {
	    this.item = item;
	    this.next = next;
	    this.prev = prev;
	}
    }

    private Node first;
    private Node last;
    private int size;

    public Deque() {
	// construct an empty deque
    }

    public boolean isEmpty() {
	// is the deque empty?
	return this.size == 0;
    }

    public int size() {
	// return the number of items on the deque
	return this.size;
    }

    public void addFirst(Item item) {
	if (item == null) throw new NullPointerException();

	Node oldfirst = first;
	first = new Node(item, oldfirst, null);
	size++;

	if (oldfirst != null) oldfirst.prev = first;
	else last = first;
    }

    public void addLast(Item item) {
	if (item == null) throw new NullPointerException();

	Node oldlast = last;
	last = new Node(item, null, oldlast);
	size++;

	if (oldlast != null) oldlast.next = last;
	else first = last;
    }

    public Item removeFirst() {
	if (first == null) throw new NoSuchElementException();

	/* decrease size */
	size--;

	/* Save the current first node */
	Node oldfirst = first;

	/* Set the first node, an its previous node to null */
	first = oldfirst.next;
	if (first != null) first.prev = null;
	else last = null;

	return oldfirst.item;
    }

    public Item removeLast() {
	// delete and return the item at the end
	if (last == null) throw new NoSuchElementException();

	/* decrease size */
	size--;

	/* Save the current first node */
	Node oldlast = last;

	/* Set the first node, an its previous node to null */
	last = oldlast.prev;
	if (last != null) last.next = null;
	else first = null;

	return oldlast.item;
    }

    public Iterator<Item> iterator() {
	return new DequeIterator(this);
    }

    private class DequeIterator implements Iterator {
	private Node current;
	private Deque deque;

	public DequeIterator(Deque deque) {
	    this.deque = deque;
	    this.current = deque.first;
	}

	public boolean hasNext() {
	    //Returns true if the iteration has more elements.
	    return this.current != null;
	}

	public Item next() {
	    //Returns the next element in the iteration.
	    if (this.current == null) throw new NoSuchElementException();

	    Item item = this.current.item;
	    this.current = this.current.next;
	    return item;
	}

	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }
}
