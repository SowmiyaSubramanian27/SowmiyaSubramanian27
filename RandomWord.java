import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {

	public static void main(String[] args) {
		String word;
		String champion = StdIn.readString();
		double p;
		double i = 2.0;
		while (!StdIn.isEmpty()) {
			word = StdIn.readString();
			p = 1 / i++;
			if (StdRandom.bernoulli(p)) {
				champion = word;
			}
		}
		StdOut.println(champion);

	}

}
