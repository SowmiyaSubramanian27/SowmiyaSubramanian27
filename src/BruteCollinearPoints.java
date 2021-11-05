import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
	private static int segmentCount;
	private static LineSegment line[];

	public BruteCollinearPoints(Point[] points) {
		if (points == null)
			throw new IllegalArgumentException();
		int len = points.length;
		for (int i = 0; i < len; i++) {
			if (points[i] == null)
				throw new java.lang.IllegalArgumentException();
		}
		Point[] point = points.clone();
		Arrays.sort(point);
		for (int i = 0; i < len - 1; i++) {
			if (point[i].compareTo(point[i + 1]) == 0)
				throw new java.lang.IllegalArgumentException();
		}
		if (len < 4)
			return;
		ArrayList<LineSegment> storeSegments = new ArrayList<LineSegment>();
		segmentCount = 0;
		for (int i = 0; i < len - 3; i++)
			for (int j = i + 1; j < len - 2; j++)
				for (int k = j + 1; k < len - 1; k++)
					for (int l = k + 1; l < len; l++) {
						double s1 = point[i].slopeTo(point[j]);
						double s2 = point[i].slopeTo(point[k]);
						double s3 = point[i].slopeTo(point[l]);
						if (s1 == s2 && s1 == s3) {
							storeSegments.add(new LineSegment(point[i], point[l]));
							segmentCount++;
						}
					}
		line = new LineSegment[storeSegments.size()];
		line = storeSegments.toArray(new LineSegment[storeSegments.size()]);
	}

	public int numberOfSegments() {
		return segmentCount;
	}

	public LineSegment[] segments() {
		return line.clone();
	}

}
