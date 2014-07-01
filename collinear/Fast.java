import java.util.Arrays;

public class Fast {
    public static void main(String[] args) {
	In in = new In(args[0]);
	int num_of_points = in.readInt();

	StdDraw.setXscale(0, 32768);
	StdDraw.setYscale(0, 32768);

	Point[] points = new Point[num_of_points];
	for (int i = 0; i < num_of_points; i++) {
	    int x = in.readInt();
	    int y = in.readInt();
	    points[i] = new Point(x, y);
	    points[i].draw();
	}

	Arrays.sort(points);
	Point[] sortedPoints = Arrays.copyOf(points, points.length);
	Point[] segment;

	for (int pi = 0; pi < points.length; pi++) {
	    Point p = points[pi];
	    Arrays.sort(sortedPoints, p.SLOPE_ORDER);

	    double previous_slope = -111111;
	    for (int i = 0; i < points.length; i++) {
		double slope = p.slopeTo(sortedPoints[i]);
		int j = i;
		int end = 0;

		while (previous_slope == slope) {
		    if (j >= sortedPoints.length) {
			end = 1;
			break;
		    }

		    slope = p.slopeTo(sortedPoints[j]);
		    if (slope == Double.NEGATIVE_INFINITY) slope = previous_slope;
		    j++;
		}

		if ((j - i + end) >= 3) {
		    segment = Arrays.copyOfRange(sortedPoints, i-1, j-1+end);
		    segment = Arrays.copyOf(segment, segment.length+1);
		    segment[segment.length-1] = p;
		    Arrays.sort(segment);

		    if (segment[0].equals(p)) {
			printSegment(segment);
			p.drawTo(segment[segment.length-1]);
		    }
		}

		i = (j > i) ? j -1 : i; // set i to j if greater than i
		if (i < sortedPoints.length) {
		    previous_slope = p.slopeTo(sortedPoints[i]);
		}
	    }
	}
    }

    private static void printSegment(Point[] points) {
	int i = 0;
	for (; i + 1 < points.length; i++) {
	    StdOut.print(points[i]);
	    StdOut.print(" -> ");
	}
	StdOut.print(points[i]);
	StdOut.println();
    }

    private static void printPoints(Point[] points, Point point) {
	StdOut.println(point);
	for (int i = 0; i < points.length; i++) {
	    StdOut.print(points[i]);
	    StdOut.print(point.slopeTo(points[i]));
	    StdOut.print("|");
	}
	StdOut.println();
    }

    private static boolean isCollinear(Point p, Point q, Point r, Point s) {
	double slope_pq = p.slopeTo(q);
	double slope_pr = p.slopeTo(r);
	double slope_ps = p.slopeTo(s);
	if (slope_pq != slope_pr) return false;
	if (slope_pq != slope_ps) return false;
	return true;
    }
}
