import java.util.Iterator;
import java.util.Stack;

public class QueueUsingStack {
	public Stack<Integer> eQueue;
	public Stack<Integer> dQueue;
	public Iterator<Integer> eIterator;
	public Iterator<Integer> dIterator;

	private void QueueUsingStack() {
		this.eQueue = new Stack<Integer>();
		this.dQueue = new Stack<Integer>();
		this.eIterator = eQueue.iterator();
		this.dIterator = dQueue.iterator();
	}

	private void enQueue(int key) {
		if (dQueue.empty()) {
			eQueue.push(key);
		} else {
			while (dIterator.hasNext())
				eQueue.add(dIterator.next());
			eQueue.push(key);
		}
	}

	private void deQueue() {
		if (dQueue.empty()) {
			while (eIterator.hasNext())
				dQueue.add(dIterator.next());
			dQueue.pop();
		} else
			dQueue.pop();
	}
}
