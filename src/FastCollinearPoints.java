import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
	private static LineSegment line[];
	private static int segmentCount;

	public FastCollinearPoints(Point[] points) {
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
		for (int i = 0; i < len; i++) {
			Arrays.sort(point);
			Arrays.sort(point, point[i].slopeOrder());
			for (int p = 0, first = 1, last = 2; last < len; last++) {
				while (last < len
						&& (Double.compare((point[p].slopeTo(point[first])), (point[p].slopeTo(point[last]))) == 0))
					last++;
				if (last - first >= 3 && point[p].compareTo(point[first]) < 0) {
					storeSegments.add(new LineSegment(point[p], point[last - 1]));
					segmentCount++;
				}
				first = last;
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

	public static void main(String[] args) {
		Point[] points = new Point[8];
		points[0] = new Point(10000, 0);
		points[1] = new Point(0, 10000);
		points[2] = new Point(3000, 7000);
		points[3] = new Point(7000, 3000);
		points[4] = new Point(20000, 2000);
		points[5] = new Point(2000, 20000);
		points[6] = new Point(8000, 2000);
		points[7] = new Point(10000, 8000);

		// draw the points
		StdDraw.enableDoubleBuffering();
		StdDraw.setXscale(0, 32768);
		StdDraw.setYscale(0, 32768);
		for (Point p : points) {
			p.draw();
		}
		StdDraw.show();

		// print and draw the line segments
		FastCollinearPoints collinear = new FastCollinearPoints(points);
		for (LineSegment segment : collinear.segments()) {
			StdOut.println(segment);
			segment.draw();
		}
		StdDraw.show();
	}
}