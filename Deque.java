import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
	private Node first;
	private Node last;
	private int count;

	private class Node {
		Node previous;
		Node next;
		Item val;
	}

	// construct an empty deque
	public Deque() {
		this.first = null;
		this.last = null;
		this.count = 0;

	}

	// is the deque empty?
	public boolean isEmpty() {
		return (size() == 0);

	}

	// return the number of items on the deque
	public int size() {
		return count;
	}

	// add the item to the front
	public void addFirst(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node newNode = new Node();
		newNode.previous = null;
		newNode.val = item;
		newNode.next = null;
		if (isEmpty()) {
			this.first = newNode;
			this.last = newNode;
		} else {
			newNode.next = first;
			first.previous = newNode;
		}
		first = newNode;
		count++;
	}

	// add the item to the back
	public void addLast(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node newNode = new Node();
		newNode.previous = null;
		newNode.val = item;
		newNode.next = null;
		if (isEmpty()) {
			this.first = newNode;
			this.last = newNode;
		} else {
			last.next = newNode;
			newNode.previous = last;
		}
		last = newNode;
		count++;
	}

	// remove and return the item from the front
	public Item removeFirst() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		count--;
		Item val = first.val;
		first = first.next;
		if (first != null)
			first.previous = null;
		else
			last = first;
		return val;
	}

	// remove and return the item from the back
	public Item removeLast() {
		if (isEmpty())
			throw new java.util.NoSuchElementException();
		count--;
		Item val = last.val;
		last = last.previous;
		if (last != null)
			last.next = null;
		else
			first = last;
		return val;
	}

	public Iterator<Item> iterator() {
		return new DequeIterator();
	}

	private class DequeIterator implements Iterator<Item> {
		private Node head;

		// return an iterator over items in order from front to back
		public DequeIterator() {
			this.head = first;
		}

		public boolean hasNext() {
			return !(head == null);
		}

		public Item next() {
			if (head == null)
				throw new java.util.NoSuchElementException();
			Item val = head.val;
			head = head.next;
			return val;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	public static void main(String[] args) {
		Deque<Integer> deque = new Deque<>();
		System.out.println(deque.isEmpty());
		deque.addFirst(2);
		System.out.println(deque.removeLast());
		deque.addLast(4);
		System.out.println(deque.removeLast());
		deque.addLast(3);
		System.out.println(deque.removeFirst());
		deque.addFirst(1);
		System.out.println(deque.removeFirst());
		deque.addFirst(1);
		Iterator<Integer> ir = deque.iterator();
		while (ir.hasNext())
			System.out.println(ir.next());

		
	}

}