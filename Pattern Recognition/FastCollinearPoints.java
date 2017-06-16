import java.util.ArrayList;
import java.util.Arrays;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    
    private LineSegment[] segments;
    
    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        // check if the input array is null or any elment is null
        if (points == null || points.length == 0) 
            throw new NullPointerException();       
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new NullPointerException();
        }
        // check if there are any dupicate points
        Arrays.sort(points);
        for (int i = 1; i < points.length; i++) {        
            if (points[i].compareTo(points[i-1]) == 0) {
                throw new IllegalArgumentException();
            } 
        }
        Point[] aux = points.clone();
        ArrayList<LineSegment> lineSegments = new ArrayList<>();      
        for (int i = 0; i < points.length-3; i++) {
            Point base = points[i];
            Arrays.sort(aux,base.slopeOrder());
            int first = 0;
            int last = first+1;
            Point minPoint;
            Point maxPoint;
            while (last < aux.length) {
                minPoint = aux[first];
                maxPoint = aux[first];
                while (last < aux.length && base.slopeTo(aux[first]) == base.slopeTo(aux[last])){
                    if (minPoint.compareTo(aux[last]) > 0) {
                        minPoint = aux[last];
                    }                  
                    if (maxPoint.compareTo(aux[last]) < 0) {
                        maxPoint = aux[last];
                    }
                    last++;
                }
                if ( last - first >= 3 && base.compareTo(minPoint) <= 0) {
                        lineSegments.add(new LineSegment(base,maxPoint));     
                }             
                first = last;  
                last++;             
            }
        }
        segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
    
    // the number of line segments
    public int numberOfSegments() {
        if (segments == null) return 0;
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}