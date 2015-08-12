/*************************************************************************
 * Name:
 * Email:
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/


import java.util.Arrays;
import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new bySlopeOrder();       // YOUR DEFINITION HERE
    
    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

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

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (that == null)
            throw new NullPointerException();
        
        if (this.x == that.x)
        {
            if (this.y == that.y)
            {
                return Double.NEGATIVE_INFINITY;
            }
            else
            {
                return Double.POSITIVE_INFINITY;
            }
        }
        else if (this.y == that.y)
        {
            return +0;
        }
        else 
            return ((double) that.y - (double) this.y)/((double) that.x - (double) this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        if (that == null)
            throw new NullPointerException();
        
        /* YOUR CODE HERE */
        if ((this.y < that.y) || (this.y == that.y && this.x < that.x))
        {
            // Return less
            return -1;
        }
        else if (this.y == that.y && this.x == that.x)
        {
            // Return equal
            return 0;
        }
        else
        {
            // Return greater
            return 1;
        }
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    
    private class bySlopeOrder implements Comparator<Point> 
    {

        @Override
        public int compare(Point p0, Point p1) {
            // TODO Auto-generated method stub
            Point p = Point.this;
            
            double a = p0.slopeTo(p);
            double b = p1.slopeTo(p);
            
            if (a > b)
                return 1;
            else if (a == b)
                return 0;
            else
                return -1;

        }
        
    }
    
    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
        Point[] p = new Point[3];
        p[0] = new Point(3, 4);
        p[1] = new Point(1, 3);
        p[2] = new Point(11, 2);
        
        for (Point P : p)
        {
            System.out.println(P);
        }
        
        Arrays.sort(p, p[0].SLOPE_ORDER);
        
        
        for (Point P : p)
        {
            System.out.println(P);
        }
    }
    
}
