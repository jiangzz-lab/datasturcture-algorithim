package algorithm.dp;

import java.util.Comparator;
import java.util.Arrays;

public class LargestSetOfPointsWithPositveSlope {

	static class Point {
		public int x;
		public int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class XComparator implements Comparator<Point> {
		@Override
		public int compare(Point p1, Point p2) {
			if (p1.x == p2.x) {
				return 0;
			}
			return p1.x < p2.x ? -1 : 1;
		}
	}

	public int largest(Point[] points) {
		if (points.length < 2) {
			return 0;
		}
		Arrays.sort(points, new XComparator());
		int[] M = new int[points.length];
		M[0] = 1;
		int max = 1;
		for (int i = 1; i < points.length; i++) {
			M[i] = 1;
			for (int j = 0; j < i; j++) {
				if (points[j].y < points[i].y) {
					M[i] = Math.max(M[i], M[j] + 1);
				}
			}
			max = Math.max(max, M[i]);
		}
		return max >= 2 ? max : 0;
	}
	
	public static void main(String[] args) {
		Point[] points = new Point[] {new Point(0, 0), new Point(1, 1), new Point(2, 3), new Point(3, 3)};
		LargestSetOfPointsWithPositveSlope solu = new LargestSetOfPointsWithPositveSlope();
		System.out.println(solu.largest(points));
		points = new Point[] {new Point(1, 2), new Point(1, 2)};
		System.out.println(solu.largest(points));
	}

}
