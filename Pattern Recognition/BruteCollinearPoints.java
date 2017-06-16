import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
    
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) 
            throw new NullPointerException();
        // check if any repeated point or null point
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
            if (i > 0 && points[i].compareTo(points[i-1]) == 0) {
                throw new IllegalArgumentException();
            } 
        }
        ArrayList<LineSegment> lineSegments = new ArrayList<>();
        Arrays.sort(points);
        for (int i = 0; i < points.length-3; i++) {
            for (int j = i + 1; j < points.length-2; j++) {
                for (int k = j + 1; k < points.length-1; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        if (points[i].slopeTo(points[j])  == points[k].slopeTo(points[l]) &&
                    points[i].slopeTo(points[k]) == points[i].slopeTo(points[l]))
                            lineSegments.add(new LineSegment(points[i],points[l]));
                    }
                }     
            }
        }
        segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
    
    // the number of line segments
    public int numberofSegments() {
        return segments.length;
    
    }
    
    // the line segments
    public LineSegment[] segments() {
        return segments;
    }
    
    
    public static void main(String[] args) {
        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }
        
        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();
        
        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
    
}