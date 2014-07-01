import java.util.Comparator;

public class Point implements Comparable<Point> {
    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new SlopeComparator();

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    private class SlopeComparator implements Comparator<Point> {
	public int compare(Point p1, Point p2) {
	    if (p1 == null || p2 == null) throw new NullPointerException();
	    if (Point.this.slopeTo(p1) < Point.this.slopeTo(p2)) return -1;
	    if (Point.this.slopeTo(p1) > Point.this.slopeTo(p2)) return +1;
	    return 0;
	}
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
	if (that == null) throw new NullPointerException();
	if (this.y == that.y && this.x == that.x) return Double.NEGATIVE_INFINITY;
	if (this.y == that.y) return +0;
	if (this.x == that.x) return Double.POSITIVE_INFINITY;

	return (double)(that.y-this.y)/(that.x-this.x);
    }

    public int compareTo(Point that) {
	if (this.y < that.y) return -1;

	if (this.y == that.y) {
	    if (this.x == that.x) return 0;
	    if (this.x < that.x)  return -1;
	}

	return +1;
    }

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
	StdOut.println(new Point(9, 9).slopeTo(new Point(4, 6)));
	StdOut.println(new Point(10875, 27893).slopeTo(new Point(24476, 8961)));
	StdOut.println(new Point(320, 64).slopeTo(new Point(159, 405)));
    }
}
