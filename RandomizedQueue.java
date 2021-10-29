import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Node head;
	private int count;

	private class Node {
		Node next;
		Item val;
	}

	// construct an empty randomized queue
	public RandomizedQueue() {
		this.head = null;
		this.count = 0;
	}

	// is the randomized queue empty?
	public boolean isEmpty() {
		return (size() == 0);
	}

	// return the number of items on the randomized queue
	public int size() {
		return count;
	}

	// add the item
	public void enqueue(Item item) {
		if (item == null)
			throw new IllegalArgumentException();
		Node newNode = new Node();
		newNode.val = item;
		newNode.next = null;
		if (!isEmpty())
			newNode.next = head;
		this.head = newNode;
		count++;
	}

	// remove and return a random item
	public Item dequeue() {
		if (head == null)
			throw new java.util.NoSuchElementException();
		count--;
		int randomNumber;
		if (count == 0)
			randomNumber = 1;
		else
			randomNumber = StdRandom.uniform(1, count + 2);
		if (randomNumber == 1) {
			Item val = head.val;
			head = head.next;
			return val;
		} else {
			Node cursor = head;
			while (randomNumber-- != 2)
				cursor = cursor.next;
			Node previousNode = cursor;
			cursor = cursor.next;
			Item val = cursor.val;
			previousNode.next = cursor.next;
			return val;
		}
	}

	// return a random item (but do not remove it)
	public Item sample() {
		if (head == null)
			throw new java.util.NoSuchElementException();
		if (count == 1)
			return head.val;
		Node cursor = head;
		int randomNumber = StdRandom.uniform(1, count + 1);
		while (randomNumber-- != 1)
			cursor = cursor.next;
		return cursor.val;
	}

	// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new RandomQueueIterator();
	}

	private class RandomQueueIterator implements Iterator<Item> {
		private Node current;
		private boolean[] arr;
		private int displayCount = size();

		// return an iterator over items in order from front to back
		public RandomQueueIterator() {
			this.current = head;
			this.arr = new boolean[size()];
		}

		public boolean hasNext() {
			return (displayCount != 0);
		}

		public Item next() {
			current = head;
			if (displayCount == 0)
				throw new java.util.NoSuchElementException();
			displayCount--;
			int randomNumber = StdRandom.uniform(1, size() + 1);
			while (arr[randomNumber-1])
				randomNumber = StdRandom.uniform(1, size() + 1);
			arr[randomNumber-1] = true;
			while ((randomNumber--) != 1)
				current = current.next;
			return current.val;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

	// unit testing (required)
	public static void main(String[] args) {
		RandomizedQueue<Integer> queue = new RandomizedQueue<>();
		queue.enqueue(27);
		queue.enqueue(4);
		queue.enqueue(9);
		queue.enqueue(217);
		queue.enqueue(40);
		queue.enqueue(965);
		queue.enqueue(274);
		queue.enqueue(424);
		queue.enqueue(97);
		Iterator<Integer> it1 = queue.iterator();
		Iterator<Integer> it2 = queue.iterator();
		while(it1.hasNext())
			System.out.println(it1.next()+"           "+it2.next());

	}

}