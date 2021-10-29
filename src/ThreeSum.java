import java.lang.reflect.Array;
import java.util.Arrays;

public class ThreeSum {
	public ThreeSum(int[] num) {
		Arrays.sort(num);
		int len = num.length;
		for (int i = 0; i < len - 2; i++) {
			for (int j = i + 1; j < len - 1; j++) {
				int need = -(num[i] + num[j]);
				int index = Arrays.binarySearch(num, j + 1, len, need);
				if (index >= 0)
					System.out.println(num[index] + "," + num[i] + "," + num[j]);
			}
		}
	}

	private static void find(int[] num, int key) {
		int len = num.length;
		int mid = len >>> 1;
		while (!((num[mid] > num[mid - 1]) && (num[mid] > num[mid + 1]))) {
			if (num[mid] < num[mid - 1])
				mid--;
			if (num[mid] < num[mid + 1])
				mid++;
		}
		System.out.println(num[mid]);
		if (Arrays.binarySearch(num, 0, mid, key) >= 0)
			System.out.println(key + " is found in the given bitonic array");
		else if (reverseBinarySearch(num, len - 1, mid + 1, key))
			System.out.println(key + " is found in the given bitonic array");
		else
			System.out.println(key + " is not found in the given bitonic array");
	}

	private static boolean reverseBinarySearch(int[] num, int fromIndex, int toIndex, int key) {
		rangeCheck(num.length, fromIndex, toIndex);
		int low = toIndex - 1;
		int high = fromIndex;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			int midVal = num[mid];
			if (midVal > key)
				low = mid + 1;
			else if (midVal < key)
				high = mid - 1;
			else
				return true; // key found
		}
		return false; // key not found.
	}

	static void rangeCheck(int arrayLength, int fromIndex, int toIndex) {
		if (fromIndex < toIndex) {
			throw new IllegalArgumentException("fromIndex(" + fromIndex + ") < toIndex(" + toIndex + ")");
		}
		if (fromIndex > arrayLength) {
			throw new ArrayIndexOutOfBoundsException(fromIndex);
		}
		if (toIndex < 0) {
			throw new ArrayIndexOutOfBoundsException(toIndex);
		}
	}

	public static void main(String[] arg) {
		int[] num = { 0, 2, 3, 4, 5, 6, 7, 5, 4, 3, 2, 1, 0, -1, -2, -100 };
		find(num, 4);
	}
}