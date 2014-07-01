import java.util.Arrays;

public class Brute {
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
	for (int p = 0; p < num_of_points - 3; p++) {
	    for (int q = p + 1; q < num_of_points - 2; q++) {
		for (int r = q + 1; r < num_of_points - 1; r++) {
		    for (int s = r + 1; s < num_of_points; s++) {
			Point[] segment = new Point[] {points[p], points[q], points[r], points[s]};
			if (isCollinear(points[p], points[q], points[r], points[s])) {
			    printSegment(segment);
			}
		    }
		}
	    }
	}
    }

    private static void printSegment(Point[] points) {
	Arrays.sort(points);
	int i = 0;
	for (; i + 1 < points.length; i++) {
	    StdOut.print(points[i]);
	    StdOut.print(" -> ");
	}
	StdOut.print(points[i]);
	points[0].drawTo(points[i]);
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
