import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {
    
    private LineSegment[] segments;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        if (points == null || points.length == 0) 
            throw new NullPointerException();
        // check if any repeated point or null point
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length; i++) {
            if (pointsCopy[i] == null) throw new NullPointerException();
            if (i > 0 && pointsCopy[i].compareTo(pointsCopy[i-1]) == 0) {
                throw new IllegalArgumentException();
            } 
        }
        ArrayList<LineSegment> lineSegments = new ArrayList<>();
        for (int i = 0; i < pointsCopy.length-3; i++) {
            for (int j = i + 1; j < pointsCopy.length-2; j++) {
                for (int k = j + 1; k < pointsCopy.length-1; k++) {
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        if (pointsCopy[i].slopeTo(pointsCopy[j])  == pointsCopy[k].slopeTo(pointsCopy[l]) &&
                    pointsCopy[i].slopeTo(pointsCopy[k]) == pointsCopy[i].slopeTo(pointsCopy[l]))
                            lineSegments.add(new LineSegment(pointsCopy[i], pointsCopy[l]));
                    }
                }     
            }
        }
        segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
    }
    
    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    
    }
    
    // the line segments
    public LineSegment[] segments() {
        return segments.clone();
    }
    
}