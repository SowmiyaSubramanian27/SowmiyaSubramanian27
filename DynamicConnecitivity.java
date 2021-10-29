import java.util.Scanner;

public class DynamicConnecitivity {
	static int[] array;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		UF(n);
		union(1, 8);
		if (connected(1, 9))
			System.out.println("yes");
		else
			System.out.println("no");
		scan.close();

	}

	public static void union(int a, int b) {
		int ra = array[a];
		int rb = array[b];
		if (ra > rb) {
			array[rb] = ra;
		} else {
			array[ra] = rb;
		}
	}

	private static int find(int a) {
		while (array[a] != a)
			a = array[a];
		return array[a];
	}

	public static boolean connected(int a, int b) {

		return array[a] == array[b];
	}

	public static int count() {
		return 1;

	}

	public static void UF(int n) {
		array = new int[n];
		int i = 0;
		while (i < n) {
			array[i] = i++;
		}

	}
}
