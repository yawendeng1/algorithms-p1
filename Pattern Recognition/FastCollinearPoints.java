import java.util.ArrayList;
import java.util.Arrays;

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
        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        for (int i = 1; i < pointsCopy.length; i++) {        
            if (pointsCopy[i].compareTo(pointsCopy[i-1]) == 0) {
                throw new IllegalArgumentException();
            } 
        }
        Point[] aux = pointsCopy.clone();
        ArrayList<LineSegment> lineSegments = new ArrayList<>();      
        for (int i = 0; i < pointsCopy.length-3; i++) {
            Point base = pointsCopy[i];
            Arrays.sort(aux,base.slopeOrder());
            int first = 0;
            int last = first+1;
            Point minPoint;
            Point maxPoint;
            while (last < aux.length) {
                minPoint = aux[first];
                maxPoint = aux[first];
                while (last < aux.length && base.slopeTo(aux[first]) == base.slopeTo(aux[last])) {
                    if (minPoint.compareTo(aux[last]) > 0) {
                        minPoint = aux[last];
                    }                  
                    if (maxPoint.compareTo(aux[last]) < 0) {
                        maxPoint = aux[last];
                    }
                    last++;
                }
                if (last - first >= 3 && base.compareTo(minPoint) <= 0) {
                        lineSegments.add(new LineSegment(base, maxPoint));     
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
        return segments.clone();    
    }
}
    