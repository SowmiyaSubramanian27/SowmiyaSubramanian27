import java.util.Iterator;

import edu.princeton.cs.algs4.StdIn;

public class Permutation {

	public static void main(String[] args) {
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		while (!StdIn.isEmpty()) {
			queue.enqueue(StdIn.readString());
		}
		Iterator<String> itr = queue.iterator();
		int count = Integer.valueOf(args[0]);
		while (count-- != 0)
			System.out.println(itr.next());

	}

}
